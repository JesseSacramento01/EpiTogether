package com.example.fragmentst.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fragmentst.R
import com.example.fragmentst.databinding.FragmentConsultaBinding
import com.example.fragmentst.db.Consulta
import com.example.fragmentst.viewmodel.ConsultaAdapter



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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addConsulta.setOnClickListener {
            findNavController().navigate(R.id.action_consulta_to_consultaForm)
        }
    }
}