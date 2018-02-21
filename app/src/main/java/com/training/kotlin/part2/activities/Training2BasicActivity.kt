package com.training.kotlin.part2.activities

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.bvchopda.fragmentsdemo.R
import com.kotlin.android.BaseActivity
import com.kotlin.android.adapters.ItemsAdapter
import kotlinx.android.synthetic.main.activity_training_1_basic.*

/**
 * Created by cygnet on 10/1/18.
 */
class Training2BasicActivity : BaseActivity() {

    private var mList = listOf(
        "1. Primary & Secondary constructor",
        "2. Prepare Inheritance class demo with" +
                "\n\t2.1 Overriding Methods" +
                "\n\t2.2 Overriding Properties"/*,
        "3. Extension function",
        "4. if..else condition",
        "5. When Expression",
        "6. Single expression function",
        "7. While and doWhile loop",
        "8. Call function in expression",
        "9. Function with nullable argument",
        "10. Range expression",
        "11. Basic class and their instance",
        "12. Call the multiple functions in sequence using \"WITH\" for instance created of class",
        "13. Lambda expression",
        "14. Set, map and list for mutable and immutable",
        "15. Singleton object and call it to any other KT file",
        "16. Null check without if/when",
        "17. Execute block of code if variable not null without if/when",
        "18. Break and continue at label"*/
)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_1_basic)
        if (rvBasicTasks.layoutManager == null) {
            logd("getLayoutManager == null")
            rvBasicTasks.setHasFixedSize(true)
            val linearLayoutManager = LinearLayoutManager(this)
            val dividerItemDecoration = DividerItemDecoration(rvBasicTasks.context, linearLayoutManager.orientation)
            rvBasicTasks.addItemDecoration(dividerItemDecoration)
            rvBasicTasks.layoutManager = linearLayoutManager
            rvBasicTasks.itemAnimator = DefaultItemAnimator()
        } else {
            logd("getLayoutManager != null")
        }
        val mAdapter = ItemsAdapter(mList, object : ItemsAdapter.OnItemClickListener {
            override fun onItemClicked(index: Int) {
                logd("Index clicked $index")
                val aBundle = Bundle()
                aBundle.putInt("index", index)
                loadActivity(Training2BasicSolutionActivity::class.java, aBundle)
            }
        })
        rvBasicTasks.adapter = mAdapter
    }
}