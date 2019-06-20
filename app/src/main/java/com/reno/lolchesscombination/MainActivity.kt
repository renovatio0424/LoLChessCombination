package com.reno.lolchesscombination

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.reno.lolchesscombination.model.Champion
import com.reno.lolchesscombination.view.ModalBottomSheet
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_combination_list.*

class MainActivity : AppCompatActivity() {

    private val combinationList = Champion.mockList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_combinations.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CombinationAdapter().apply {
                addChampions(combinationList)
                notifyDataSetChanged()

            }
        }

        bottom_my_list
    }
}