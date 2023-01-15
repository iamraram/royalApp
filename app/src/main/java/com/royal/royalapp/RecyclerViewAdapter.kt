package com.royal.royalapp

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val recyclerViewItems : ArrayList<ItemData>):
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return RecyclerViewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.bind(recyclerViewItems[position], position)
    }

    override fun getItemCount(): Int {
        return recyclerViewItems.size
    }

    inner class RecyclerViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val date: TextView = itemView.findViewById(R.id.date)
        private val leagueName: TextView = itemView.findViewById(R.id.league_name)
        private val firstTeam: TextView = itemView.findViewById(R.id.first_team)
        private val secondTeam: TextView = itemView.findViewById(R.id.second_team)
        private val odds: TextView = itemView.findViewById(R.id.conclude)
        private val result: TextView = itemView.findViewById(R.id.conclude2)

        @SuppressLint("SetTextI18n")
        fun bind(ItemData: ItemData, i: Int) {
            if (i == recyclerViewItems.size - 1) {
                odds.setPadding(0, 0, 0, 120)
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