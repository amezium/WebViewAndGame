package com.glu.testwork.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.glu.testwork.R

class GameAdapter(val clickItem: ClickItem): RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    var listGame = arrayListOf<Int>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    interface ClickItem{
        fun onClickItem(position: Int)
    }


    inner class GameViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imGameId = itemView.findViewById<ImageView>(R.id.imGame)
        init {
            itemView.setOnClickListener {
                clickItem.onClickItem(layoutPosition)
                Log.d("Log.d", "$layoutPosition")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.imGameId.setImageResource(listGame[position])
    }

    override fun getItemCount() = listGame.size
}