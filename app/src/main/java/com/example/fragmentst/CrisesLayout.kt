package com.example.fragmentst

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.fragmentst.databinding.FragmentCrisesLayoutBinding
import com.example.fragmentst.db.Crise
import com.example.fragmentst.model.CriseViewModel
import com.example.fragmentst.model.CriseViewModelFactory
import com.example.fragmentst.model.CrisesAdapter
import com.example.fragmentst.model.MedicationAdapter
import com.example.fragmentst.repository.CriseRepository
import kotlinx.coroutines.launch


class CrisesLayout : Fragment() {

    private lateinit var binding : FragmentCrisesLayoutBinding
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
}