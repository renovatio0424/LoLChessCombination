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

class CombinationAdapter(private val onChampionClick: (champion:Champion) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), MainContract.CombinationAdapter.View,
    MainContract.CombinationAdapter.Model {

    private var combinationRuleMap: HashMap<CombinationRule, ArrayList<Champion>> = HashMap()
    //TODO : Use RxJava
    override var selectChampionList: ArrayList<Champion> = ArrayList()

    override fun updateSelectChampionView(champions: Champion) {
        selectChampionList.add(champions)
        notifyDataSetChanged()
    }

    override fun getSelectedChampion(position: Int): Champion? {
        return if (selectChampionList.size >= position) selectChampionList[position] else null
    }

    override fun getSelectedChampionPosition(selectedChampions: Champion): Int {
        return selectChampionList.indexOf(selectedChampions)
    }

    override fun addCombinationList(championList: ArrayList<Champion>) {
        for (champion in championList) {
            for (combination in champion.combinations) {
                if (combinationRuleMap.containsKey(combination))
                    combinationRuleMap[combination]?.add(champion)
                else
                    combinationRuleMap[combination] = arrayListOf(champion)
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_combination, parent, false)
        return CombinationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return combinationRuleMap.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is CombinationViewHolder)
            return

        val currentRuleItem = ArrayList(combinationRuleMap.keys)[position]

        holder.combinationTitle.text = currentRuleItem.name
        holder.rvChampionList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ChampionAdapter(onChampionClick).apply {
                combinationRuleMap[currentRuleItem]?.let { addChampions(it) }
            }
        }
    }
}

class CombinationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val combinationTitle: TextView = view.combinationTitle
    val rvChampionList: RecyclerView = view.rv_champion_list
}
