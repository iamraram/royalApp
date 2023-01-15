package com.royal.royalapp

import android.annotation.SuppressLint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.royal.royalapp.databinding.ActivityFirstBinding
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

@Suppress("UNCHECKED_CAST")
class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val recyclerViewItems = ArrayList<ItemData>()

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("apple", "DocumentSnapshot")

        val path = db.collection("post").document("data")
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