package com.reno.lolchesscombination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reno.lolchesscombination.model.Champion
import com.reno.lolchesscombination.model.CombinationRule
import kotlinx.android.synthetic.main.item_combination.view.*

class CombinationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var combinationRuleMap: HashMap<CombinationRule, ArrayList<Champion>> = HashMap()
    private val selectChampion:HashMap<CombinationRule, ArrayList<Champion>> = HashMap()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_combination, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return combinationRuleMap.size
    }

    fun addChampions(champions: ArrayList<Champion>) {
        for (champion in champions) {
            for (combination in champion.combinations) {
                if (combinationRuleMap.containsKey(combination))
                    combinationRuleMap[combination]?.add(champion)
                else
                    combinationRuleMap[combination] = arrayListOf(champion)
            }
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is ViewHolder)
            return

        val combinationList = ArrayList(combinationRuleMap.keys)
        val currentRuleItem = combinationList[position]

        holder.combinationTitle.text = currentRuleItem?.name
        holder.rvChampionList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ChampionAdapter().apply {
                combinationRuleMap[currentRuleItem]?.let { addChampionList(it) }
            }
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val combinationTitle: TextView = view.combinationTitle
    val rvChampionList: RecyclerView = view.rv_champion_list
}
