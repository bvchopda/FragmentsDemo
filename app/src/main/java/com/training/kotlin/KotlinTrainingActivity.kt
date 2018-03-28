package com.training.kotlin

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.bvchopda.fragmentsdemo.R
import com.kotlin.android.BaseActivity
import com.kotlin.android.adapters.ItemsAdapter
import com.training.kotlin.part1.activities.Training1BasicActivity
import kotlinx.android.synthetic.main.activity_kotlin_training.*
import java.util.*

class KotlinTrainingActivity : BaseActivity() {

    private var mList: List<String> = ArrayList(Arrays.asList("1. Basic", "2. Basic 2"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_training)

        if (rvItems.layoutManager == null) {
            logd("getLayoutManager == null")
            rvItems.setHasFixedSize(true)
            val linearLayoutManager = LinearLayoutManager(this)
            val dividerItemDecoration = DividerItemDecoration(rvItems.context, linearLayoutManager.orientation)
            rvItems.addItemDecoration(dividerItemDecoration)
            rvItems.layoutManager = linearLayoutManager
            rvItems.itemAnimator = DefaultItemAnimator()
        } else {
            logd("getLayoutManager != null")
        }
        val mAdapter = ItemsAdapter(mList, object : ItemsAdapter.OnItemClickListener {
            override fun onItemClicked(index: Int) {
                logd("Index clicked $index")
                when (index) {
                    0 -> {
                        loadActivity(Training1BasicActivity::class.java, null)
                    }
                }
            }
        })
        rvItems.adapter = mAdapter
    }
}
