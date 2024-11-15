package com.example.fragmentst.viewmodel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.fragmentst.R
import com.example.fragmentst.databinding.HealthItemBinding
import com.example.fragmentst.db.Medicacao

class MedicationAdapter(
    private val context: Context,
    private val medicationList: List<Medicacao>
    ) : BaseAdapter() {

    private lateinit var binding: HealthItemBinding

        override fun getCount(): Int {
            return medicationList.size
        }

        override fun getItem(position: Int): Any {
            return medicationList[position]
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

            val medication = medicationList[position]
            binding.medicamentoIcon.setImageResource(R.drawable.baseline_medical_services_24)
            binding.medicationName.text = "Medicamento: ${medication.nomeMedicamento}"
            binding.medicationDate.text = "Data: ${medication.dataInicio}"
            binding.medicationDose.text = "Dose: ${medication.dose}mg"
            binding.medicationRegimen.text = "Regime: ${medication.regime}"

            return view
        }
    }
