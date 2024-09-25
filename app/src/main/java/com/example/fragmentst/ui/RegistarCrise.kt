package com.example.fragmentst.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fragmentst.R
import com.example.fragmentst.databinding.FragmentRegistarCriseBinding


class RegistarCrise : Fragment() {

    private lateinit var binding:FragmentRegistarCriseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRegistarCriseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.manifestacoes.setOnClickListener {
            findNavController().navigate(R.id.action_registarCrise_to_manifestacoes2)
        }

        binding.registerButton.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
                .setTitle("Registrado")
                .setMessage("Crise Registrada com Sucesso! ")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                    findNavController().navigate(R.id.action_registarCrise_to_inicio2)
                }

            dialogBuilder.create().show()
        }
    }

}