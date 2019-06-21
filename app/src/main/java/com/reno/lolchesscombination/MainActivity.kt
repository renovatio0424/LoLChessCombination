package com.reno.lolchesscombination

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.reno.lolchesscombination.model.Champion
import kotlinx.android.synthetic.main.activity_main.*

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

        button_hide.setOnClickListener {
            if (it.visibility == View.VISIBLE)
                total_combination_layout.visibility = View.GONE
            else
                total_combination_layout.visibility = View.VISIBLE
        }
    }
}