package com.example.fragmentst.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fragmentst.R
import com.example.fragmentst.databinding.FragmentSaudeBinding


class Saude : Fragment() {

    private lateinit var binding: FragmentSaudeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSaudeBinding.inflate(inflater,container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.medicamentoSection.setOnClickListener {
            findNavController().navigate(R.id.action_saude_to_medicamentos)
        }

        binding.consultaSection.setOnClickListener {
            findNavController().navigate(R.id.action_saude_to_consulta)
        }
    }
}