package com.example.fragmentst

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fragmentst.databinding.FragmentCriarContaBinding
import com.example.fragmentst.db.Crise
import com.example.fragmentst.db.Utilizador
import com.example.fragmentst.model.CriseViewModel
import com.example.fragmentst.model.CriseViewModelFactory
import com.example.fragmentst.model.UtilizadorViewModel
import com.example.fragmentst.model.UtilizadorViewModelFactory
import com.example.fragmentst.repository.CriseRepository
import com.example.fragmentst.repository.UtilizadorRepository
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class CriarConta : Fragment() {

    lateinit var binding: FragmentCriarContaBinding

    private var datePicker: MaterialDatePicker<Long>? = null

    private lateinit var utilizadorViewModel: UtilizadorViewModel
    var spGenero = ""
    var tipoUtilizador = ""

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
        binding = FragmentCriarContaBinding.inflate(inflater, container, false)

        val generoSpinner: Spinner = binding.spGenero
        val generoItems = listOf("Masculino", "Feminino", "Outro")

        val generoAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, generoItems)
        generoSpinner.adapter = generoAdapter

        generoSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long
            ) {
                spGenero = generoItems[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


        val utilizadorSpinner: Spinner = binding.spTipoUtilizador
        val utilizadorItems = listOf("Prof de saúde", "Adolescente", "Prof. de Educação", "Pais")

        val utilizadorAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, utilizadorItems)
        utilizadorSpinner.adapter = utilizadorAdapter


        utilizadorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long
            ) {
                tipoUtilizador = utilizadorItems[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}


        }


        val application = requireActivity().application

        val repository = UtilizadorRepository(application)

        val factory = UtilizadorViewModelFactory(application, repository)
        utilizadorViewModel = ViewModelProvider(this, factory)[UtilizadorViewModel::class.java]



        lifecycleScope.launch {
            val user = utilizadorViewModel.getUtilizadorById(5)  // Calling the suspend function

            if (user != null) {
                if (user.nome == "") {
                    Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT)
                        .show()
                }
                Toast.makeText(requireContext(), "User found: ${user.nome}", Toast.LENGTH_SHORT)
                    .show()
                Toast.makeText(requireContext(), "User found: ${user.email}", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create the MaterialDatePicker
        datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Birthdate")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds()) // Optional: default to today
            .build()


        binding.tvDataNascimento.setOnClickListener {

            if (!datePicker!!.isAdded) {
                datePicker!!.show(parentFragmentManager, "MATERIAL_DATE_PICKER")

                datePicker!!.addOnPositiveButtonClickListener { selection ->

                    val selectedDate = Date(selection)
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    binding.tvDataNascimento.text = dateFormat.format(selectedDate)
                }
            }

            binding.registarButton.setOnClickListener {

                val name = binding.etNome.text.toString()
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()
                val birthDate = binding.tvDataNascimento.text.toString()
                val tel = binding.etTelefone.text.toString()
                val address = binding.etEndereco.text.toString()

                val utilizador = Utilizador(
                    name, email, password, tipoUtilizador, birthDate, spGenero,
                    tel, address
                )

                lifecycleScope.launch {
                    utilizadorViewModel.insertData(utilizador)
                    val dialogBuilder = AlertDialog.Builder(requireContext())
                        .setTitle("Registo de Conta")
                        .setMessage("Conta Registrada com Sucesso!")
                        .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    dialogBuilder.create().show()

                    findNavController().navigate(R.id.action_criarConta_to_login)
                }
            }

        }
    }
}