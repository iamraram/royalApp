package com.royal.royalapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.royal.royalapp.databinding.ActivityThirdBinding

@Suppress("UNCHECKED_CAST")
class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val recyclerViewItems = ArrayList<ItemData>()

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("apple", "DocumentSnapshot")

        binding.joinButton.setOnClickListener {
            Toast.makeText(this, "인앱 결제 추가 예정", Toast.LENGTH_SHORT).show()
        }

        val path = db.collection("premium").document("data")
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
                        }                }
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