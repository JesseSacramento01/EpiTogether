package com.example.fragmentst

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentst.databinding.FragmentConsultaBinding
import com.example.fragmentst.db.Consulta
import com.example.fragmentst.model.ConsultaAdapter



class Consulta : Fragment() {

    private lateinit var binding: FragmentConsultaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentConsultaBinding.inflate(inflater, container, false)

        val consultaList = listOf(
            Consulta(1, 12, "12/10/2024", "Médico", "Rua Honduras 12", "Comparecer, ao Local 2"),
        )

        val gridView = binding.consultaGridView
        val adapter = ConsultaAdapter(requireContext(), consultaList)
        gridView.adapter = adapter

        return binding.root
    }
}