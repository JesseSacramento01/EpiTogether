package com.example.fragmentst

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentst.databinding.FragmentMedicamentosBinding
import com.example.fragmentst.db.Medicacao
import com.example.fragmentst.model.MedicationAdapter


class Medicamentos : Fragment() {

    private lateinit var binding: FragmentMedicamentosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMedicamentosBinding.inflate(inflater, container, false)


        val medicationList = listOf(
            Medicacao(1, 1100, "Ymed", "Diariamente",
                "10/10/2024", "20/10/2024", "Diario", "10:30",
                true),
                    Medicacao(3, 1100, "5med", "Diariamente",
            "10/10/2024", "22/10/2024", "Diario", "11:30",
            true)
        )

        val gridView = binding.medicationGridView
        val adapter = MedicationAdapter(requireContext(), medicationList)
        gridView.adapter = adapter

        return binding.root
    }
}