package com.reno.lolchesscombination

import com.reno.lolchesscombination.model.Champion
import com.reno.lolchesscombination.model.CombinationRule

interface MainContract {
    interface View {
        fun addChampionAtMyCombination(champions: Champion, position: Int)
        fun removeChampionAtMyCombination(champions: Champion, position: Int)
        fun showCombinationDescription(selectChampionMap: HashMap<CombinationRule, ArrayList<Champion>>?)
    }

    interface Presenter {
        fun selectChampion(champions: Champion)
    }

    interface CombinationAdapter {
        interface View {
            fun updateSelectChampionView(champions: Champion)
        }

        interface Model {
            var selectChampionList: ArrayList<Champion>
            fun getSelectedChampion(position: Int): Champion?
            fun getSelectedChampionPosition(selectedChampions: Champion): Int
            fun addCombinationList(championList: ArrayList<Champion>)
        }
    }

    interface ChampionAdapter {
        interface View {
            fun enableChampionView(champions: Champion, enable: Boolean)
        }

        interface Model {
            fun addChampions(championList: ArrayList<Champion>)
        }
    }

    interface Model {
        fun loadCombinationData()
    }
}