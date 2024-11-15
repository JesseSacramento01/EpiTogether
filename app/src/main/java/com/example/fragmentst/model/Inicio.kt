package com.example.fragmentst.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import com.example.fragmentst.databinding.FragmentInicioBinding

class Inicio : Fragment() {

    private lateinit var binding: FragmentInicioBinding

    // Declare a property for the binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    // If you need to interact with UI elements, do it in onViewCreated()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Access and manipulate your views here
        // Example: Setup a CalendarView listener
        val calendarView: CalendarView = binding.calendarView
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Handle date selection

        }
    }
}
