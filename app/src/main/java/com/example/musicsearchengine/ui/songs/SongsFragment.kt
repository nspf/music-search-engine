package com.example.musicsearchengine.ui.songs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.musicsearchengine.MainActivity
import com.example.musicsearchengine.R
import com.example.musicsearchengine.database.SongEntity
import com.example.musicsearchengine.databinding.FragmentSongsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SongsFragment : Fragment() {

    var collectionId: Long = 0

    private val viewModel: SongsViewModel by viewModel { parametersOf(collectionId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val album = SongsFragmentArgs.fromBundle(requireArguments()).album
        collectionId = album.collectionId

        (requireActivity() as MainActivity).supportActionBar?.title = album.collectionName

        val binding = FragmentSongsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.recyclerView.adapter = SongsAdapter(SongsAdapter.OnClickListener { view: View, song: SongEntity? ->
                val extras = FragmentNavigatorExtras(
                    view to view.transitionName
                )
                val bundle = bundleOf("song" to song)
                findNavController().navigate(R.id.songPreviewFragment, bundle, null, extras)


        })
        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.list_divider)?.let { dividerItemDecoration.setDrawable(it) }
        binding.recyclerView.addItemDecoration(dividerItemDecoration)

        viewModel.songsLiveData.observe(viewLifecycleOwner) {
            (binding.recyclerView.adapter as SongsAdapter).submitList(it)
        }

        postponeEnterTransition()
        binding.recyclerView.viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                    true
                }


        return binding.root
    }

}