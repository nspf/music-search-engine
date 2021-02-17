package com.example.musicsearchengine.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicsearchengine.database.ArtistEntity
import com.example.musicsearchengine.databinding.ArtistItemBinding

class ArtistAdapter(private val onClickListener: OnClickListener) :
    PagedListAdapter<ArtistEntity, ArtistAdapter.ArtistViewHolder>(DiffCallback) {

    class ArtistViewHolder(private var binding: ArtistItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(artist: ArtistEntity) {
            binding.artist = artist
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ArtistEntity>() {
        override fun areItemsTheSame(oldItem: ArtistEntity, newItem: ArtistEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ArtistEntity, newItem: ArtistEntity): Boolean {
            return oldItem.artistId == newItem.artistId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(ArtistItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = getItem(position)
        artist?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            onClickListener.onClick(artist)
        }
    }

    class OnClickListener(val clickListener: (artist: ArtistEntity?) -> Unit) {
        fun onClick(artist: ArtistEntity?) = clickListener(artist)
    }
}