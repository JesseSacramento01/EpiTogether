package com.example.fragmentst.ui

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.fragmentst.R
import com.example.fragmentst.databinding.FragmentRegistarCriseBinding
import java.util.Calendar


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

        binding.textViewDate.setOnClickListener {
            showDatePickerDialog(binding.textViewDate)
        }
    }

    private fun showDatePickerDialog(selectedDateTextView: TextView) {
        // Get the current date
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(requireContext(), R.style.CustomDatePickerDialog,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                // Set the selected date to the TextView
                val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                selectedDateTextView.text = date
            }, year, month, day)

        // Show the dialog
        datePickerDialog.show()
    }
  }