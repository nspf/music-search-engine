package com.example.musicsearchengine.ui.songs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.musicsearchengine.database.SongEntity
import com.example.musicsearchengine.databinding.SongItemBinding
import kotlinx.android.synthetic.main.song_item.view.*

class SongsAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<SongEntity, SongsAdapter.SongViewHolder>(DiffCallback) {

    class SongViewHolder(private var binding: SongItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(song: SongEntity) {
            binding.song = song
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<SongEntity>() {
        override fun areItemsTheSame(oldItem: SongEntity, newItem: SongEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SongEntity, newItem: SongEntity): Boolean {
            return oldItem.trackId == newItem.trackId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(SongItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = getItem(position)
        song?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            onClickListener.onClick(holder.itemView.song_image, song)
        }
    }

    class OnClickListener(val clickListener: (view: View, album: SongEntity?) -> Unit) {
        fun onClick(view: View, song: SongEntity?) = clickListener(view, song)
    }
}