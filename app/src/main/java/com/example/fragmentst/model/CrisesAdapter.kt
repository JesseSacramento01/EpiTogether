package com.example.fragmentst.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.fragmentst.R
import com.example.fragmentst.databinding.HealthItemBinding
import com.example.fragmentst.db.Crise

class CrisesAdapter(
    private val context: Context,
    private val crisesList: List<Crise>
) : BaseAdapter() {

    private lateinit var binding: HealthItemBinding
    override fun getCount(): Int {
        return crisesList.size
    }

    override fun getItem(position: Int): Any {
        return crisesList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View

        if (convertView == null) {
            binding =
                HealthItemBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            view = convertView
            binding = view.tag as HealthItemBinding
        }

        val crises = crisesList[position]
        binding.medicamentoIcon.setImageResource(R.drawable.brain)
        binding.medicationName.text = "Data: ${crises.data}"
        binding.medicationDate.text = "Profissional: ${crises.data}"
        binding.medicationDose.text = "Local: ${crises.localDaCrise}mg"
        binding.medicationRegimen.text = "Notas: ${crises.hora}"

        return view

    }
}