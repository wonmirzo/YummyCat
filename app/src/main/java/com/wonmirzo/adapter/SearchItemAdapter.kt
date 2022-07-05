package com.wonmirzo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.wonmirzo.R
import com.wonmirzo.network.model.SearchItem


class SearchItemAdapter(
    private var items: ArrayList<SearchItem>,
) :
    RecyclerView.Adapter<SearchItemAdapter.VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemAdapter.VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchItemAdapter.VH, position: Int) {
        val item = items[position]

        val ivPhoto = holder.ivPhoto

        Glide.with(ivPhoto.context)
            .load(item.url)
            .fitCenter()
            .into(ivPhoto)

    }

    override fun getItemCount(): Int = items.size

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val ivPhoto: ShapeableImageView = view.findViewById(R.id.ivPhoto)
    }
}