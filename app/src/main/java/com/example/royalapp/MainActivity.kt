package com.example.royalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.royalapp.databinding.ActivityMainBinding
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val joinButton = binding.joinButton
        joinButton.setOnClickListener {

            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}