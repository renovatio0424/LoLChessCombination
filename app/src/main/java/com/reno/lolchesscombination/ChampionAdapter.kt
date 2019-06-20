package com.reno.lolchesscombination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.reno.lolchesscombination.model.Champion
import kotlinx.android.synthetic.main.item_champion.view.*

class ChampionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var championList:ArrayList<Champion> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_champion, null)
        return ChampionRowHolder(view)
    }

    override fun getItemCount(): Int {
        return championList.size
    }

    fun addChampionList(items: ArrayList<Champion>){
        this.championList = items
        notifyDataSetChanged()
    }

    fun refresh(){
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is ChampionRowHolder)
            return

        val championItem = championList[position]
        holder.ivChampion.setOnClickListener { Toast.makeText(holder.itemView.context, championItem.name, Toast.LENGTH_LONG).show() }
        holder.tvChampionName.text = championItem.name
    }

}

class ChampionRowHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvChampionName: TextView = view.tvTitle
    val ivChampion: ImageView = view.itemImage
}
