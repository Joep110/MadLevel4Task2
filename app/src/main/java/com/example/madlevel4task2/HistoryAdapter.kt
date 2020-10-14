package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.item_game.view.*

class PortalAdapter(private val portals: List<Game>) : RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun databind(game: Game) {
            if (game.result == "WIN") {
                itemView.tvResult.text = "You win"
            } else if (game.result == "LOSE") {
                itemView.tvResult.text = "Computer wins"
            } else {
                itemView.tvResult.text = game.result
            }
            itemView.tvTime.text = game.date.toString()
            when (game.computerMove.toInt()) {
                1 -> itemView.ivComputerResult.setImageResource(R.drawable.paper)
                2 -> itemView.ivComputerResult.setImageResource(R.drawable.rock)
                3 -> itemView.ivComputerResult.setImageResource(R.drawable.scissors)
            }
            when (game.playerMove.toInt()) {
                1 -> itemView.ivPlayerResult.setImageResource(R.drawable.paper)
                2 -> itemView.ivPlayerResult.setImageResource(R.drawable.rock)
                3 -> itemView.ivPlayerResult.setImageResource(R.drawable.scissors)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortalAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false))
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    override fun onBindViewHolder(holder: PortalAdapter.ViewHolder, position: Int) {
        holder.databind(portals[position])
    }
}