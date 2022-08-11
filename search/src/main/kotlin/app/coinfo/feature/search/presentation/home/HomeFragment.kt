package app.coinfo.feature.search.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.coinfo.feature.search.R
import app.coinfo.feature.search.databinding.SearchFragmentHomeBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class HomeFragment : Fragment(R.layout.search_fragment_home) {

    private val binding: SearchFragmentHomeBinding by viewBinding(SearchFragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.backClickEvent.observe(viewLifecycleOwner) {
        }
        viewModel.searchClickEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.destination_search_fragment)
        }
    }
}
