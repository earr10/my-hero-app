package mx.com.earr.myheroapp

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.com.earr.myheroapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val SUPERHERO_KEY = "superhero"
        const val HERO_BITMAP_KEY = "bitmap"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras!!
        val superhero = bundle.getParcelable<Superhero>(SUPERHERO_KEY)!!

        binding.superhero = superhero

        val bitmapDirectory = bundle.getString(HERO_BITMAP_KEY)

        val bitmap = BitmapFactory.decodeFile(bitmapDirectory)

        if (bitmap != null) {
            binding.ivHero.setImageBitmap(bitmap
            )
        }
    }
}