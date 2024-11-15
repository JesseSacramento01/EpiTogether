package com.example.fragmentst.model

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fragmentst.R
import com.example.fragmentst.databinding.FragmentConsultaFormBinding

class ConsultaForm : Fragment() {

    lateinit var binding : FragmentConsultaFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentConsultaFormBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
                .setTitle("Consulta Registada")
                .setMessage("Consulta Registada com Sucesso!")
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            dialogBuilder.create().show()

            findNavController().navigate(R.id.action_consultaForm_to_saude)
        }
    }


}