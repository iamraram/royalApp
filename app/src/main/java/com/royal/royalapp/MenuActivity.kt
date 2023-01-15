package com.royal.royalapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.royal.royalapp.databinding.ActivityMenuBinding
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    private lateinit var rewardedAd: RewardedAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btn1 = binding.button1
        btn1.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }

        val btn2 = binding.button2
        btn2.setOnClickListener {
            Toast.makeText(this@MenuActivity, "잠시만 기다려주세요...", Toast.LENGTH_SHORT).show()
            MobileAds.initialize(this) {}
            rewardedAd = RewardedAd(this, "ca-app-pub-2426272595059032/8084421884")
            val adLoadCallback = object: RewardedAdLoadCallback() {
                override fun onRewardedAdLoaded() {
                    val activityContext: Activity = this@MenuActivity
                    val adCallback = object: RewardedAdCallback() {
                        override fun onRewardedAdOpened() {
                            Log.d("dd", "onRewardedAdOpened")
                        }
                        override fun onRewardedAdClosed() {
                            Log.d("dd", "onRewardedAdClosed")
                        }
                        override fun onUserEarnedReward(reward: RewardItem) {
                            Log.d("dd", "onUserEarnedReward")
                            Toast.makeText(this@MenuActivity, "보상을 받았습니다.", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@MenuActivity, SecondActivity::class.java)
                            startActivity(intent)
                        }
                        override fun onRewardedAdFailedToShow(adError: AdError) {
                            Log.d("dd", "onRewardedAdFailedToShow")
                        }
                    }
                    rewardedAd.show(activityContext, adCallback)
                }
                override fun onRewardedAdFailedToLoad(adError: LoadAdError) {
                    Log.d("dd", "onRewardedAdFailedToLoad: $adError")
                }
            }
            rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)

        }

        val btn3 = binding.button3
        btn3.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}