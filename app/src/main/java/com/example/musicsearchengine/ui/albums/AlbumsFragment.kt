package com.example.musicsearchengine.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.musicsearchengine.MainActivity
import com.example.musicsearchengine.databinding.FragmentAlbumsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AlbumsFragment : Fragment() {

    var artistId: Long = 0

    private val viewModel: AlbumsViewModel by viewModel { parametersOf(artistId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val artist = AlbumsFragmentArgs.fromBundle(requireArguments()).artist
        artistId = artist.artistId

        val binding = FragmentAlbumsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.recyclerView.adapter = AlbumAdapter(AlbumAdapter.OnClickListener {
            it?.let {
                findNavController().navigate(
                    AlbumsFragmentDirections.actionAlbumsFragmentToSongsFragment(it)
                )
            }
        })

        viewModel.albums.observe(viewLifecycleOwner) { pagedList ->
            (binding.recyclerView.adapter as AlbumAdapter).submitList(pagedList)
        }

        (requireActivity() as MainActivity).apply {
            supportActionBar?.title = artist.artistName
            showToolbar()
            hideBottomNavigation()
        }

        return binding.root
    }

}