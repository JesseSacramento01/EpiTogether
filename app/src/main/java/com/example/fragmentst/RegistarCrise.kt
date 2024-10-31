package com.example.fragmentst

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.NumberPicker
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.fragmentst.databinding.FragmentRegistarCriseBinding
import com.example.fragmentst.db.Crise
import com.example.fragmentst.model.CheckBoxViewModel
import com.example.fragmentst.model.CriseViewModel
import com.example.fragmentst.model.CriseViewModelFactory
import com.example.fragmentst.model.SharedViewModel
import com.example.fragmentst.repository.CriseRepository
import kotlinx.coroutines.launch
import java.util.Calendar


class RegistarCrise : Fragment() {

    private lateinit var binding:FragmentRegistarCriseBinding

    private lateinit var videoPickerLauncher: ActivityResultLauncher<Intent>

    private val sharedViewModel : SharedViewModel by activityViewModels()
    private lateinit var criseViewModel: CriseViewModel
    private val checkBoxViewModel: CheckBoxViewModel by activityViewModels()


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


        // Initialize ActivityResultLauncher
        videoPickerLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val selectedVideoUri: Uri? = data?.data
                // Handle the selected video URI here
                //if (selectedVideoUri != null) {

                //}
            }
        }

        val activitySpinner: Spinner = binding.atividadeSpinner
        val activityItems = listOf("Acordado", "A dormir", "Exercício Físico")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, activityItems)
        activitySpinner.adapter = adapter

        activitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long
            ) {
                // Save the selected option to the ViewModel
                sharedViewModel.activitySpinner = activityItems[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        sharedViewModel.activitySpinner?.let { savedOption ->
            val position = activityItems.indexOf(savedOption)
            if (position >= 0) {
                activitySpinner.setSelection(position)
            }
        }

        val cycleSpinner : Spinner = binding.ciclo
        val cycleItems = listOf("Manhã", "À Tarde", "À Noite")

        val cycleAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cycleItems)
        cycleSpinner.adapter = cycleAdapter

        cycleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long
            ) {
                sharedViewModel.cycleSpinner = cycleItems[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        sharedViewModel.cycleSpinner?.let { savedOption ->
            val position = cycleItems.indexOf(savedOption)
            if (position >= 0) {
                cycleSpinner.setSelection(position)
            }
        }

        val locationSpinner : Spinner = binding.locationSpinner
        val locationItems = listOf("Casa", "Escola", "Rua", "Hospital")

        val locationAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, locationItems)
        locationSpinner.adapter = locationAdapter

        locationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long
            ) {
                sharedViewModel.locationSpinner = locationItems[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        sharedViewModel.locationSpinner?.let { savedOption ->
            val position = locationItems.indexOf(savedOption)
            if (position >= 0) {
                locationSpinner.setSelection(position)
            }
        }


        val application = requireActivity().application


        val repository = CriseRepository(application)


        val factory = CriseViewModelFactory(application, repository)
        criseViewModel = ViewModelProvider(this, factory)[CriseViewModel::class.java]



        lifecycleScope.launch {
            val crise = criseViewModel.getCriseById(id)  // Calling the suspend function

            if (crise != null) {
                Toast.makeText(requireContext(), "User found: ${crise.data}", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Restore data from ViewModel
        val date = sharedViewModel.date
        val time = sharedViewModel.time
        val duration = sharedViewModel.duration

        // Set restored data back to the views
        binding.textViewDate.text = date
        binding.timeText.text = time
        binding.durationText.text = duration


        binding.manifestacoes.setOnClickListener {
            findNavController().navigate(R.id.action_registarCrise_to_manifestacoes2)
        }

        // When the button clicked a notification appears
        binding.registerButton.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
                .setTitle("Registrado")
                .setMessage("Crise Registrada com Sucesso! ")
                .setPositiveButton("OK") { dialog, _ ->

                    val currentCrise = Crise(sharedViewModel.idUtilizador,
                        sharedViewModel.date, sharedViewModel.time,
                        sharedViewModel.duration, sharedViewModel.cycleSpinner,
                        checkBoxViewModel.tipoMovimento, checkBoxViewModel.localizacaoMovimento,
                        checkBoxViewModel.corDaPele, checkBoxViewModel.respiracao,
                        checkBoxViewModel.estadoDeConciencia, checkBoxViewModel.outrasManifestacoes,
                        null,sharedViewModel.locationSpinner, sharedViewModel.activitySpinner,null)


                    lifecycleScope.launch {
                        criseViewModel.insertData(currentCrise)
                    }

                    lifecycleScope.launch {
                        criseViewModel.itemList.observe(viewLifecycleOwner) { _ ->
                                addItem(currentCrise)
                        }
                    }

                    dialog.dismiss()
                    findNavController().navigate(R.id.action_registarCrise_to_inicio2)
                    setSharedViewModelVarToNull()

                }
                .setNegativeButton("Cancel", null)

            dialogBuilder.create().show()


        }

        binding.textViewDate.setOnClickListener {
            showDatePickerDialog(binding.textViewDate)
        }

        binding.timeText.setOnClickListener{
                showTimePicker { hour, minute ->
                    val formattedTime = String.format("%02d:%02d".lowercase(), hour, minute)
                    binding.timeText.text = getString(R.string.time_selected, formattedTime)
                    sharedViewModel.time = binding.timeText.text.toString()
                }
        }

        binding.durationText.setOnClickListener {
            showDurationPickerDialog()
        }

        binding.videoButton.setOnClickListener {
            pickVideo()
        }
    }

    private fun showDatePickerDialog(selectedDateTextView: TextView) {

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
                sharedViewModel.date = selectedDateTextView.text.toString()
            }, year, month, day)

        // Show the dialog
        datePickerDialog.show()
    }

    private fun pickVideo() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType( MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "video/*")
        videoPickerLauncher.launch(intent)  // Launch the video picker
    }

    /**
     * onTimeSelected -> (Int)
     */
    private fun showTimePicker(onTimeSelected: (Int, Int) -> Unit) {
        // Get the current time as default values
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        // Create a TimePickerDialog
        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                // Pass the selected time to the callback
                onTimeSelected(hourOfDay, minute)
            },
            currentHour, currentMinute, true
        )
        timePickerDialog.show()  // Show the time picker dialog
    }

    private fun showDurationPickerDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_duration_picker, null)

// Initialize the NumberPickers for minutes and seconds using dialogView
        val minutePicker = dialogView.findViewById<NumberPicker>(R.id.minute_picker)
        val secondPicker = dialogView.findViewById<NumberPicker>(R.id.second_picker)

        // Set the default values (you can customize this based on the use case)
        minutePicker.value = 0
        secondPicker.value = 0

        minutePicker.minValue = 0
        minutePicker.maxValue = 59

        secondPicker.minValue = 0
        secondPicker.maxValue = 59

        minutePicker.wrapSelectorWheel = true
        secondPicker.wrapSelectorWheel = true



        // Create and show the AlertDialog
        AlertDialog.Builder(requireContext())
            .setTitle("Select Duration")
            .setView(dialogView)
            .setPositiveButton("OK") { _, _ ->

                // Get the selected minute and second values
                val selectedMinutes = minutePicker.value
                val selectedSeconds = secondPicker.value

                // Handle the selected duration (e.g., display it in a TextView or store it)
                val formattedDuration = String.format("%02d:%02d".lowercase(), selectedMinutes, selectedSeconds)
                Toast.makeText(
                    requireContext(),
                    "Selected Duration: $formattedDuration",
                    Toast.LENGTH_LONG
                ).show()

                binding.durationText.text = formattedDuration
                sharedViewModel.duration = binding.durationText.text.toString()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun addItem(item: Crise) {
        val currentList = criseViewModel.itemList.value.orEmpty().toMutableList()
        currentList.add(item)
        criseViewModel.setItems(currentList)
    }

    private fun setSharedViewModelVarToNull() {
        sharedViewModel.time = null
        sharedViewModel.date = null
        sharedViewModel.duration = null
        sharedViewModel.cycleSpinner = null
        sharedViewModel.locationSpinner = null
        sharedViewModel.activitySpinner = null
    }
}