package com.royal.royalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.royal.royalapp.databinding.ActivityAddBinding
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var list: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val joinButton = binding.backButton
        joinButton.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
        val date = intent.getStringExtra("date")
        val leagueName = intent.getStringExtra("leagueName")
        val firstTeam = intent.getStringExtra("firstTeam")
        val secondTeam = intent.getStringExtra("secondTeam")
        val odds = intent.getStringExtra("odds")
        val path = intent.getStringExtra("path")
        val id = intent.getStringExtra("id")

        binding.et1.setText(date)
        binding.et2.setText(leagueName)
        binding.et3.setText(firstTeam)
        binding.et4.setText(secondTeam)
        binding.et5.setText(odds)

        binding.joinButton2.setOnClickListener {
            db.collection(path!!).document("data").update(id!!, makeData())
            Toast.makeText(this, "적용되었습니다.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.delete.setOnClickListener {
            db.collection(path!!).document("data").update(id!!, "deleted")
            Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun makeData(): List<String> {
        var date = binding.et1.text.toString()
        date = try {
            date.substring(0 until 4) + ". " +
                    date.substring(6 until 8) + ". " +
                    date.substring(10 until 12)
        } catch (ex: Exception) {
            "error"
        }
        val leagueName = binding.et2.text.toString()
        val homeTeam = binding.et3.text.toString()
        val awayTeam = binding.et4.text.toString()
        val odds = binding.et5.text.toString()
        val result = binding.et6.text.toString()

        list = listOf(date, leagueName, homeTeam, awayTeam, odds, result)
        return list

    }
}