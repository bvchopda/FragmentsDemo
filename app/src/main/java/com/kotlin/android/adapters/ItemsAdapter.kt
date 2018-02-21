package com.kotlin.android.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bvchopda.fragmentsdemo.R
import kotlinx.android.synthetic.main.layout_module_item.view.*

/**
 * Created by cygnet on 3/1/18.
 */
class ItemsAdapter(private val mList: List<String>?, private var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_module_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemsAdapter.ViewHolder, position: Int) {
        holder.bind(mList!![position])
        holder.itemView.setOnClickListener({
            onItemClickListener?.onItemClicked(holder.layoutPosition)
        })
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    class ViewHolder
    (itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(mItem: String) {
            itemView.tvTitle.text = mItem
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(index: Int)
    }

}