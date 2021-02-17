package com.example.musicsearchengine.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.musicsearchengine.MainActivity
import com.example.musicsearchengine.R
import com.example.musicsearchengine.api.ApiStatus
import com.example.musicsearchengine.databinding.FragmentSearchBinding
import com.example.musicsearchengine.utils.hideSoftKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        val binding = FragmentSearchBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.list_divider)?.let { dividerItemDecoration.setDrawable(it) }
        binding.recyclerView.addItemDecoration(dividerItemDecoration)

        binding.recyclerView.adapter = ArtistAdapter(ArtistAdapter.OnClickListener {
            it?.let {
                findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToAlbumsFragment(it)
                )
            }
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (viewModel.lastSearchQuery != query) {
                    viewModel.searchLiveData.value = query
                    viewModel.lastSearchQuery = query
                }
                requireActivity().hideSoftKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        viewModel.searchResults.observe(viewLifecycleOwner) { pagedList ->
            (binding.recyclerView.adapter as ArtistAdapter).submitList(pagedList)
            binding.emptyView.visibility = View.GONE
        }

        viewModel.apiStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                ApiStatus.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.emptyView.visibility = View.GONE
                }
                ApiStatus.ERROR -> {
                    Toast.makeText(context, getString(R.string.error_api_request), Toast.LENGTH_LONG)
                    .show()
                    binding.progress.visibility = View.GONE
                    binding.emptyView.visibility = View.GONE

                }
                ApiStatus.DONE -> {
                    binding.progress.visibility = View.GONE
                    binding.emptyView.visibility = View.GONE
                }
            }
        }

        (requireActivity() as MainActivity).apply {
            showToolbar()
            showBottomNavigation()
        }

        return binding.root
    }

}