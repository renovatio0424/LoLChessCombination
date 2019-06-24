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

class ChampionAdapter(private val onChampionClick: (champion:Champion) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    MainContract.ChampionAdapter.View, MainContract.ChampionAdapter.Model {

    private var championList: LinkedHashMap<Champion, Boolean> = LinkedHashMap()

    override fun enableChampionView(champions: Champion, enable: Boolean) {
        championList[champions] = enable
        notifyDataSetChanged()
    }

    override fun addChampions(championList: ArrayList<Champion>) {
        for (champion in championList)
            this.championList[champion] = false
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_champion, null)
        return ChampionRowHolder(view)
    }

    override fun getItemCount(): Int {
        return championList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is ChampionRowHolder)
            return

        val championItem = championList.toList()[position]

        holder.ivChampion.apply {
            setImageResource(if(championItem.second) R.drawable.ic_check_black_24dp else R.drawable.ic_launcher_foreground)
            setOnClickListener {
                championList[championItem.first] = true
                onChampionClick(championItem.first)
                notifyDataSetChanged()

                Toast.makeText(
                    holder.itemView.context,
                    championItem.first.name,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        holder.tvChampionName.text = championItem.first.name
    }

}

class ChampionRowHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvChampionName: TextView = view.tvTitle
    val ivChampion: ImageView = view.itemImage
}
