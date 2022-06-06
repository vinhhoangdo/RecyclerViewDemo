package com.example.rvd.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

/**
 * Provide a reference to the type of views that you are using
 * (custom ViewHolder).
 */
class ViewHolder(view: View, listener: MyAdapter.onItemClickListener) : RecyclerView.ViewHolder(view) {
    val titleView: TextView
    val subtitleView: TextView
    val imageView: ImageView

    init {
        // Define click listener for the ViewHolder's View.
        titleView = view.titleView
        subtitleView = view.subtitleView
        imageView = view.imageView

        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }

    }
}