package com.example.musicsearchengine.ui.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicsearchengine.database.AlbumEntity
import com.example.musicsearchengine.databinding.AlbumItemBinding

class AlbumAdapter(private val onClickListener: OnClickListener) :
        PagedListAdapter<AlbumEntity, AlbumAdapter.AlbumViewHolder>(DiffCallback) {

    class AlbumViewHolder(private var binding: AlbumItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(album: AlbumEntity) {
            binding.album = album
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<AlbumEntity>() {
        override fun areItemsTheSame(oldItem: AlbumEntity, newItem: AlbumEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AlbumEntity, newItem: AlbumEntity): Boolean {
            return oldItem.collectionId == newItem.collectionId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(AlbumItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        album?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            onClickListener.onClick(album)
        }
    }

    class OnClickListener(val clickListener: (album: AlbumEntity?) -> Unit) {
        fun onClick(album: AlbumEntity?) = clickListener(album)
    }
}