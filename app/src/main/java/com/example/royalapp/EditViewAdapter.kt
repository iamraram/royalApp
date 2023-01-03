package com.example.royalapp

import android.R.attr.targetId
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class EditViewAdapter(private val recyclerViewItems : ArrayList<ItemData2>):
    RecyclerView.Adapter<EditViewAdapter.EditViewHolder>() {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.edit_item, parent, false)
        return EditViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EditViewHolder, position: Int) {
        holder.bind(recyclerViewItems[position], position)
    }

    override fun getItemCount(): Int {
        return recyclerViewItems.size
    }

    inner class EditViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val date: TextView = itemView.findViewById(R.id.date)
        private val leagueName: TextView = itemView.findViewById(R.id.league_name)
        private val firstTeam: TextView = itemView.findViewById(R.id.first_team)
        private val secondTeam: TextView = itemView.findViewById(R.id.second_team)
        private val odds: TextView = itemView.findViewById(R.id.conclude)
        private val result: TextView = itemView.findViewById(R.id.conclude2)
        private val info: TextView = itemView.findViewById(R.id.info)

        @SuppressLint("SetTextI18n")
        fun bind(ItemData: ItemData2, i: Int) {
            itemView.setOnClickListener {
                Log.d("TAG", "dede: ${i}")
                var path: String = ""
                when (ItemData.place) {
                    "1" -> path = "post"
                    "2" -> path = "adv"
                    "3" -> path = "premium"
                }
                val intent = Intent(itemView.context, AddActivity::class.java)
                intent.putExtra("date", ItemData.date)
                intent.putExtra("leagueName", ItemData.league)
                intent.putExtra("firstTeam", ItemData.firstTeam)
                intent.putExtra("secondTeam", ItemData.secondTeam)
                intent.putExtra("odds", ItemData.odds)
                intent.putExtra("path", path)
                intent.putExtra("id", ItemData.data[1])
                itemView.context.startActivity(intent)
            }
            var path: String = ""
            when (ItemData.place) {
                "1" -> path = "post"
                "2" -> path = "adv"
                "3" -> path = "premium"
            }
            Log.d("TAG", "bind: $path -> data -> ${i.toString()}")
            val pathWay = db.collection(path).document("data")
            pathWay.get()
                .addOnSuccessListener { document ->
                    Log.d("TAG", "bind: ${document.data!![i.toString()]}")
                    }
            when (ItemData.place) {
                "1" -> info.text = "Daily 게시글입니다."
                "2" -> info.text = "Odds 게시글입니다."
                "3" -> info.text = "Premium 게시글입니다."
                else -> info.text = " "
            }
            Log.d("TAG", "bind: $i")
            date.text = ItemData.date + "   /"
            leagueName.text = ItemData.league
            firstTeam.text = ItemData.firstTeam + "   -"
            secondTeam.text = ItemData.secondTeam
            odds.text = ItemData.odds
            result.text = ItemData.result

            if (ItemData.result == "LOSE") {
                result.setTextColor(itemView.resources.getColor(R.color.red_color))
            }
            else {
                result.setTextColor(itemView.resources.getColor(R.color.green_color))
            }

        }

    }
}