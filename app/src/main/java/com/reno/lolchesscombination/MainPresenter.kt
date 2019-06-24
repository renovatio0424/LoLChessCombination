package com.reno.lolchesscombination

import com.reno.lolchesscombination.model.Champion

class MainPresenter(
    private val view: MainContract.View,
    private val adapterModel: MainContract.CombinationAdapter.Model,
    private val adapterView: MainContract.CombinationAdapter.View
) : MainContract.Presenter {
    override fun selectChampion(champion: Champion) {
        adapterView.updateSelectChampionView(champion)
        val position = adapterModel.getSelectedChampionPosition(champion)
        view.addChampionAtMyCombination(champion, position)
    }
}