package com.kangarootech.mediumapp1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.mainRecyclerView

class MainActivity : AppCompatActivity() {

    private val listAdapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        populateItems()
    }

    private fun initUi() {
        mainRecyclerView.apply {
            layoutManager = GridLayoutManager(context, ListAdapter.SPAN_SIZE)
            adapter = listAdapter
        }
    }

    private fun populateItems() {
        listAdapter.setItems(DummyItems.getItems())
    }
}
