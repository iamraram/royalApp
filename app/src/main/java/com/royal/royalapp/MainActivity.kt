package com.royal.royalapp

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.royal.royalapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var lastClickTime: Long = 0 // 마지막 클릭 시간
    private var clickTime = 0 // 클릭 된 횟수
    private val TIMES_REQUIRED = 5 // 총 필요한 클릭 횟수
    private val TIME_TIMEOUT = 2000 // 마지막 클릭후 제한시간

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val joinButton = binding.joinButton
        joinButton.setOnClickListener {

            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        binding.click.setOnClickListener {
            touchContinuously()
        }

    }

    private fun touchContinuously() {
        if (SystemClock.elapsedRealtime() - lastClickTime < TIME_TIMEOUT) {
            clickTime++
        } else {
            clickTime = 1
        }
        lastClickTime = SystemClock.elapsedRealtime()
        if (clickTime === TIMES_REQUIRED) {
            // TODO 연속 클릭 완료 후 메소드 구현
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
        }
    }

}