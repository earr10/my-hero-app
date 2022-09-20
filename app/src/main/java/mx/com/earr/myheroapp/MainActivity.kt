package mx.com.earr.myheroapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.com.earr.myheroapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val superHeroName = binding.etName.text.toString()
            val alterEgo = binding.etAlterEgo.text.toString()
            val bio = binding.etBio.text.toString()

            openDetailActivity()
        }
    }

    private fun openDetailActivity() {
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }
}