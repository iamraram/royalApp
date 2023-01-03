package com.example.royalapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.royalapp.databinding.ActivityEditBinding
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var editViewAdapter: EditViewAdapter
    private val recyclerViewItems = ArrayList<ItemData2>()

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val path1 = db.collection("post").document("data")
        val path2 = db.collection("adv").document("data")
        val path3 = db.collection("premium").document("data")

        val backButton = binding.backButton
        backButton.setOnClickListener {
            val intent = Intent(this, ChangeActivity::class.java)
            startActivity(intent)
        }

        path1.get().addOnSuccessListener { document ->
            for (i in 0 until document.data!!.size) {
                try {
                    val data: List<String> = document.data!![i.toString()] as List<String>
                    recyclerViewItems.add(ItemData2(data[0], data[1], data[2], data[3], data[4], data[5], "1", listOf("post", i.toString())))
                }
                catch (e: Exception) {
                    continue
                }
            }
            editViewAdapter.notifyDataSetChanged()
        }
        path2.get().addOnSuccessListener { document ->
            for (i in 0 until document.data!!.size) {
                try {
                    val data: List<String> = document.data!![i.toString()] as List<String>
                    recyclerViewItems.add(ItemData2(data[0], data[1], data[2], data[3], data[4], data[5], "2", listOf("adv", i.toString())))
                }
                catch (e: Exception) {
                    continue
                }
            }
            editViewAdapter.notifyDataSetChanged()
        }
        path3.get().addOnSuccessListener { document ->
            for (i in 0 until document.data!!.size) {
                try {
                    val data: List<String> = document.data!![i.toString()] as List<String>
                    recyclerViewItems.add(ItemData2(data[0], data[1], data[2], data[3], data[4], data[5], "3", listOf("premium", i.toString())))
                }
                catch (e: Exception) {
                    continue
                }
            }
            editViewAdapter.notifyDataSetChanged()
        }

        editViewAdapter = EditViewAdapter(recyclerViewItems)
        binding.editRecyclerView.adapter = editViewAdapter
    }
}