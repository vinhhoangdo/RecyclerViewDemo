package com.example.rvd.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rvd.GameDetailActivity
import com.example.rvd.R
import com.example.rvd.model.GameNFT

class MyAdapter :
    RecyclerView.Adapter<ViewHolder>() {
    private lateinit var mListener: onItemClickListener
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    var games = GameNFT(mutableListOf())
    // Create new views
    // (invoked by the layout manager AKA R.layout.item)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item, viewGroup, false)
        return ViewHolder(view, mListener)
    }

    // Replace the contents of a view
    // (invoked by the layout manager AKA R.layout.item)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from the dataset at this position and
        // replace the contents of the view with that element

        Glide.with(viewHolder.imageView.context)
            .load(
                games.assets[position].collection.banner_image_url
                    ?: "https://hoge.stream/loading.gif"
            )
            .into(viewHolder.imageView)
        viewHolder.titleView.text = games.assets[position].collection.name ?: ""
        viewHolder.subtitleView.text = games.assets[position].collection.description ?: ""
    }

    // Return the size of dataset
    override fun getItemCount() = games.assets.size
}