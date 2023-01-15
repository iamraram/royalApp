package com.royal.royalapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.royal.royalapp.R
import com.royal.royalapp.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backButton = binding.backButton
        backButton.setOnClickListener {
            finish()
        }

        val admin = binding.joinButton
        admin.setOnClickListener {
            Log.d("de", binding.secretKey.text.toString())
            if (binding.secretKey.text.toString() == "apple") {
                val intent = Intent(this, ChangeActivity::class.java)
                startActivity(intent)
            }
            else {
                binding.info.text = "키가 틀렸습니다."
                binding.info.setTextColor(ContextCompat.getColor(applicationContext!!,
                    R.color.red_color
                ))
            }
        }
    }
}