package com.example.royalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.royalapp.databinding.ActivityThirdBinding
import android.os.Bundle

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val recyclerViewItems = ArrayList<ItemData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        recyclerViewItems.add(
//            ItemData(
//                "2021-07-01",
//                "Premier League",
//                "Liverpool",
//                "Chelsea",
//                "1",
//                "1.5",
//                "1"
//            )
//        )
//
//        recyclerViewAdapter = RecyclerViewAdapter(recyclerViewItems)
//        binding.musicRecyclerView.adapter = recyclerViewAdapter

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}