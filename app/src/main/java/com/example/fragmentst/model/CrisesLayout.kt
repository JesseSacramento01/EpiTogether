package com.example.fragmentst.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentst.databinding.FragmentCrisesLayoutBinding
import com.example.fragmentst.db.Crise
import com.example.fragmentst.viewmodel.CriseViewModel
import com.example.fragmentst.viewmodel.CriseViewModelFactory
import com.example.fragmentst.viewmodel.CrisesAdapter
import com.example.fragmentst.repository.CriseRepository



class CrisesLayout : Fragment() {

    private lateinit var binding: FragmentCrisesLayoutBinding
    private lateinit var criseViewModel: CriseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCrisesLayoutBinding.inflate(inflater, container, false)

        val application = requireActivity().application
        val repository = CriseRepository(application)
        val factory = CriseViewModelFactory(application, repository)

        criseViewModel = ViewModelProvider(this, factory)[CriseViewModel::class.java]


        criseViewModel.getAllCrises().observe(viewLifecycleOwner) { criseList ->
            val gridView = binding.crisesGridView
            val adapter = CrisesAdapter(requireContext(), criseList)
            gridView.adapter = adapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.crisesGridView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedCrise = binding.crisesGridView.adapter.getItem(position) as Crise
                Toast.makeText(
                    requireContext(),
                    "Selected Crise: ${selectedCrise.idCrise}",
                    Toast.LENGTH_SHORT
                ).show() }
    }
}