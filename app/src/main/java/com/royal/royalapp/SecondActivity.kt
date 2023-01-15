package com.royal.royalapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.royal.royalapp.databinding.ActivitySecondBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

@Suppress("UNCHECKED_CAST")
class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val recyclerViewItems = ArrayList<ItemData>()

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        rewardedAd = RewardedAd(this, adTestId)
//        val adLoadCallback = object: RewardedAdLoadCallback() {
//            override fun onRewardedAdLoaded() {
//                // Ad successfully loaded.
//            }
//            override fun onRewardedAdFailedToLoad(adError: LoadAdError) {
//                // Ad failed to load.
//            }
//        }
//        rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)

        val path = db.collection("adv").document("data")
        path.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    for (i in 0 until 10000) {
                        try {
                            val data: List<String> = document.data!![i.toString()] as List<String>
                            Log.d("apple", document.data!![i.toString()].toString())
                            if (data.toString() != "deleted") {
                                recyclerViewItems.add(ItemData(data[0], data[1], data[2], data[3], data[4], data[5]))
                            }
                        }
                        catch (e: Exception) {
                            continue
                        }
                    }
                    recyclerViewAdapter.notifyDataSetChanged()
                }
                else {
                    Log.d("apple", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("apple", "get failed with ", exception)
            }

        recyclerViewAdapter = RecyclerViewAdapter(recyclerViewItems)
        binding.musicRecyclerView.adapter = recyclerViewAdapter

        binding.backButton.setOnClickListener {
            finish()
        }
    }

}