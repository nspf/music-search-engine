package com.example.musicsearchengine.ui.songpreview

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.musicsearchengine.MainActivity
import com.example.musicsearchengine.database.SongEntity
import com.example.musicsearchengine.databinding.FragmentSongPreviewBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SongPreviewFragment : Fragment() {

    lateinit var song: SongEntity

    private lateinit var binding: FragmentSongPreviewBinding

    private lateinit var player: SimpleExoPlayer

    private val viewModel: SongPreviewViewModel by viewModel { parametersOf(song.trackId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSongPreviewBinding.inflate(inflater)

        song = SongPreviewFragmentArgs.fromBundle(requireArguments()).song

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.fragment = this


        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition =  TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition()

        binding.likeIcon.setOnClickListener {
            viewModel.toggleFavoriteSong()
        }

        (requireActivity() as MainActivity).apply {
            showToolbar()
            hideBottomNavigation()
        }

        return binding.root
    }

    private fun setupExoPlayer() {
        player = SimpleExoPlayer.Builder(requireActivity()).build()
        binding.playerView.player = player

        val mediaItem: MediaItem = MediaItem.fromUri(song.previewUrl)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onStart() {
        super.onStart()
        setupExoPlayer()
    }

    override fun onStop() {
        super.onStop()
        player.release()
    }

}