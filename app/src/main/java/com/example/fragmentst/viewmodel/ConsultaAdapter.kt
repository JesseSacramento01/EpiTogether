package com.example.fragmentst.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.fragmentst.R
import com.example.fragmentst.databinding.HealthItemBinding
import com.example.fragmentst.db.Consulta

class ConsultaAdapter(
    private val context: Context,
    private val consultaList: List<Consulta>) : BaseAdapter() {
    private lateinit var binding: HealthItemBinding

    override fun getCount(): Int {
        return consultaList.size
    }

    override fun getItem(position: Int): Any {
        return consultaList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View

        if (convertView == null) {
            binding = HealthItemBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            view = convertView
            binding = view.tag as HealthItemBinding
        }

        val consulta = consultaList[position]
        binding.medicamentoIcon.setImageResource(R.drawable.baseline_medical_services_24)
        binding.medicationName.text = "Data: ${consulta.dataHora}"
        binding.medicationDate.text = "Profissional: ${consulta.tipoProfissional}"
        binding.medicationDose.text = "Local: ${consulta.local}mg"
        binding.medicationRegimen.text = "Notas: ${consulta.notas}"

        return view
    }
}
