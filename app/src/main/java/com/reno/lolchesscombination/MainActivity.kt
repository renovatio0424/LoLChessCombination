package com.reno.lolchesscombination

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.reno.lolchesscombination.model.Champion
import com.reno.lolchesscombination.model.CombinationRule
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity(), MainContract.View {
    private val myCombinationViewList: ArrayList<ImageView> by lazy {
        arrayListOf(
            iv_champion1,
            iv_champion2,
            iv_champion3,
            iv_champion4,
            iv_champion5,
            iv_champion6,
            iv_champion7,
            iv_champion8,
            iv_champion9,
            iv_champion10
        )
    }

    //Repository 에서 데이터 load
    private val combinationList = Champion.mockList

    private val combinationAdapter: CombinationAdapter by lazy {
        CombinationAdapter(onChampionClick).apply {
            addCombinationList(combinationList)
            notifyDataSetChanged()
        }
    }

    private val presenter by lazy {
        MainPresenter(this, combinationAdapter, combinationAdapter)
    }

    private val onChampionClick: (champion: Champion) -> Unit = {
        presenter.selectChampion(it)
    }

    override fun addChampionAtMyCombination(champions: Champion, position: Int) {
        val imageView = myCombinationViewList[position].apply {
            setImageResource(R.drawable.ic_check_black_24dp)
        }
        //TODO Glide
    }

    override fun removeChampionAtMyCombination(champions: Champion, position: Int) {
        val imageView = myCombinationViewList[position].apply {
            setImageResource(R.drawable.ic_add_black_24dp)
        }
        //TODO Glide
    }

    override fun showCombinationDescription(selectChampionMap: HashMap<CombinationRule, ArrayList<Champion>>?) {
        selectChampionMap?.let {
            for ((combinationRule, championList) in selectChampionMap) {
                for (i in 0 until combinationRule.combinationCount.size - 1) {
                    if (combinationRule.combinationCount[i] <= championList.size && championList.size < combinationRule.combinationCount[i + 1]) {
                        cg_my_combination_group.addView(Chip(this).apply {
                            text = combinationRule.name
                            isEnabled = false
                            setOnClickListener {
                                Toast.makeText(
                                    this@MainActivity,
                                    "$combinationRule.effectDescription[i]",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        })
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_combinations.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = combinationAdapter
        }

    }
}