package com.royal.royalapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.royal.royalapp.databinding.ActivityChangeBinding
import com.google.firebase.firestore.FirebaseFirestore

class ChangeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangeBinding
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var length: String
    private lateinit var list: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backButton = binding.backButton
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.delete.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }

        val nextBtn1 = binding.joinButton1
        val nextBtn2 = binding.joinButton2
        val nextBtn3 = binding.joinButton3

        fun change() {
            Toast.makeText(this@ChangeActivity, "업로드가 완료되었습니다!", Toast.LENGTH_SHORT).show()
            finish() //인텐트 종료
            overridePendingTransition(0, 0) //인텐트 효과 없애기
            val intent = intent //인텐트
            startActivity(intent) //액티비티 열기
            overridePendingTransition(0, 0) //
        }

        nextBtn1.setOnClickListener {
            val path = db.collection("post").document("data")
            path.get()
                .addOnSuccessListener { document ->
                    length = document.data!!.size.toString()
                    db.collection("post").document("data").update(
                        length ,makeData()
                    ).addOnSuccessListener {
                        change()
                    }.addOnFailureListener {
                        Toast.makeText(this, "문제가 발생했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("apple", "get failed with ", exception)
                }
        }

        nextBtn2.setOnClickListener {
            val path = db.collection("adv").document("data")
            path.get()
                .addOnSuccessListener { document ->
                    length = document.data!!.size.toString()
                    db.collection("adv").document("data").update(
                        length ,makeData()
                    ).addOnSuccessListener {
                        change()
                    }.addOnFailureListener {
                        Toast.makeText(this, "문제가 발생했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("apple", "get failed with ", exception)
                }
        }

        nextBtn3.setOnClickListener {
            val path = db.collection("premium").document("data")
            path.get()
                .addOnSuccessListener { document ->
                    length = document.data!!.size.toString()
                    db.collection("premium").document("data").update(
                        length ,makeData()
                    ).addOnSuccessListener {
                        change()

                    }.addOnFailureListener {
                        Toast.makeText(this, "문제가 발생했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("apple", "get failed with ", exception)
                }
        }
    }

    private fun makeData(): List<String> {
        var date = binding.et1.text.toString()
        date = try {
            date.substring(0 until 4) + ". " +
                    date.substring(4 until 6) + ". " +
                    date.substring(6 until 8)
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