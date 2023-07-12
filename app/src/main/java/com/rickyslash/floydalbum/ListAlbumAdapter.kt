package com.rickyslash.floydalbum

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rickyslash.floydalbum.databinding.LayoutItemAlbumBinding

class ListAlbumAdapter(private val listAlbums: ArrayList<Album>): RecyclerView.Adapter<ListAlbumAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: LayoutItemAlbumBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = LayoutItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listAlbums.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, desc, photo) = listAlbums[position]
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDesc.text = desc

        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.imgItemPhoto)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listAlbums[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Album)
    }

}