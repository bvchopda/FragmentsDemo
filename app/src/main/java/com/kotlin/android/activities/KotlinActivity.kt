package com.kotlin.android.activities

import android.os.Bundle
import android.support.v7.widget.*
import android.view.View
import com.example.bvchopda.fragmentsdemo.R
import com.kotlin.android.BaseActivity
import com.kotlin.android.adapters.ItemsAdapter

import kotlinx.android.synthetic.main.activity_kotlin.*
import java.util.*

class KotlinActivity : BaseActivity(), View.OnClickListener {

    private var mList: List<String> = ArrayList(Arrays.asList("Abc", "Def", "Xyz"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        btnOperation.setOnClickListener(this)
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
        val mAdapter = ItemsAdapter(mList, object: ItemsAdapter.OnItemClickListener {
            override fun onItemClicked(index: Int) {
                System.out.println("Index clicked $index")
            }
        })
        rvItems.adapter = mAdapter
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnOperation -> {
                loadActivity(OperationActivity::class.java, null)
            }
            else -> {

            }
        }
    }
}
