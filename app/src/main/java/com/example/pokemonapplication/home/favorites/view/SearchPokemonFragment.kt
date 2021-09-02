package com.example.pokemonapplication.home.favorites.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokemonapplication.R
import com.example.pokemonapplication.databinding.FragmentSearchPokemonBinding
import com.example.pokemonapplication.home.favorites.presentation.SearchPokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchPokemonFragment : Fragment() {

    private val searchPokemonViewModel by viewModel<SearchPokemonViewModel>()
    private var _binding: FragmentSearchPokemonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchPokemonBinding.inflate(layoutInflater)
        val searchTypes = resources.getStringArray(R.array.search_types)
        val arrayAdapter = ArrayAdapter(
            requireContext(), R.layout.dropdown_search_type_item, searchTypes
        )
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.autoCompleteTextView.addTextChangedListener {
            searchPokemonViewModel.onSearchTypeChanged(it.toString())
        }
        binding.searchPokeomonTextfieldEditText.addTextChangedListener {
            searchPokemonViewModel.onSearchTextChanged(it.toString())
        }
        binding.searchButton.setOnClickListener {
            searchPokemonViewModel.onSearchButtonClicked()
        }
        searchPokemonViewModel.isSearchButtonEnable.observe(viewLifecycleOwner){
            binding.searchButton.isEnabled = it
        }
        searchPokemonViewModel.searchByName.observe(viewLifecycleOwner){
            val direction =
                SearchPokemonFragmentDirections.actionSearchPokemonFragment3ToSearchPokemonNameFragment(
                    binding.searchPokeomonTextfieldEditText.text.toString()
                )
            findNavController().navigate(direction)
        }
        searchPokemonViewModel.searchByType.observe(viewLifecycleOwner){
            val direction =
                SearchPokemonFragmentDirections.actionSearchPokemonFragmentToSearchPokemonTypeFragment(
                    binding.searchPokeomonTextfieldEditText.text.toString()
                )
            findNavController().navigate(direction)
        }
    }
}