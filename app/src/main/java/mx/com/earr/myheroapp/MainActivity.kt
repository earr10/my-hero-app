package mx.com.earr.myheroapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import mx.com.earr.myheroapp.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var superheroImage: ImageView
    private var heroBitmap: Bitmap? = null
    private var picturePath = ""

    private val getContent =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success && picturePath.isNotEmpty()) {
                heroBitmap = BitmapFactory.decodeFile(picturePath)
                superheroImage.setImageBitmap(heroBitmap)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val superHeroName = binding.etName.text.toString()
            val alterEgo = binding.etAlterEgo.text.toString()
            val bio = binding.etBio.text.toString()
            val power = binding.rbPower.rating

            val hero = Superhero(superHeroName, alterEgo, bio, power)

            openDetailActivity(hero)
        }

        superheroImage = binding.ivHero
        binding.ivHero.setOnClickListener {
            openCamera()
        }
    }

    private fun openCamera() {
        val file = createImageFile()

        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(this, "$packageName.provider", file)
        } else {
            Uri.fromFile(file)
        }
        getContent.launch(uri)
    }

    private fun createImageFile(): File {
        val fileName = "superhero_image"
        val fileDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File.createTempFile(fileName, ".jpg", fileDirectory)
        picturePath = file.absolutePath
        return file
    }

    private fun openDetailActivity(
        superhero: Superhero
    ) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.SUPERHERO_KEY, superhero)
        intent.putExtra(DetailActivity.HERO_BITMAP_KEY, picturePath)
        startActivity(intent)
    }
}