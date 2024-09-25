package com.example.fragmentst.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.fragmentst.R
import com.example.fragmentst.databinding.FragmentManifestacoesBinding


class Manifestacoes : Fragment() {


    private lateinit var binding: FragmentManifestacoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentManifestacoesBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var items : List<String>
        // Get reference to the LinearLayout (the container for CheckBoxes)

        val moveButton: Button = binding.buttonTipoMovimento
        val localizacaoMovimento: Button = binding.buttonLocalizacaoDosMovimentos

        moveButton.setOnClickListener {
            items = listOf("tremores", "Espasmos Musculares", "Rígidez", "Perda de Força Muscular", "Ausência de Movimento")
            createDropdownMenu(items, moveButton)
        }

        localizacaoMovimento.setOnClickListener {
            items = listOf("Generalizados (todo o corpo)",
                    "Focais (uma parte do corpo: mão, braço, perna ou face)",
                    "Não se aplica",
                    "Espasmos Musculares", "Rígidez", "Perda de Força Muscular", "Ausência de Movimento")

            createDropdownMenu(items, localizacaoMovimento)

        }

        binding.buttonCorDaPele.setOnClickListener {
            items = listOf("Palidez","Cianose (pele azulada)", "Vermelhidão", "Outra", "Sem alteração")
            createDropdownMenu(items, binding.buttonCorDaPele)
        }

        binding.buttonRespiraO.setOnClickListener {
            items = listOf("Apneia (Ausência de Respiração)", "Respiração irregular ou ofegante", "Outra", "Sem alterações")
            createDropdownMenu(items, binding.buttonRespiraO)
        }

        binding.buttonEstadoDeConciencia.setOnClickListener {
            items = listOf("Consciente", "Inconsciente", "Sem alteração")
            createDropdownMenu(items, binding.buttonEstadoDeConciencia)

        }

        binding.buttonOutrasManifestacoes.setOnClickListener {
            items = listOf("Movimentos de mastigação", "Salivação excessiva", "Alucinações visuais ou auditivas", "Outras")
            createDropdownMenu(items, binding.buttonOutrasManifestacoes)
        }

        binding.buttonSalvar.setOnClickListener {
            //TODO Process to save the data
            findNavController().navigate(R.id.action_manifestacoes2_to_registarCrise)

        }

    }

    private fun createDropdownMenu(items:List<String>, button:Button ){
        // Inflate the popup layout
        val inflater: LayoutInflater = layoutInflater
        val popupView = inflater.inflate(R.layout.window_popup, null)

        // Access the LinearLayout inside the popupView
        val checkBoxContainer: LinearLayout = popupView.findViewById(R.id.checkboxContainer)


        // Loop through each item and create a CheckBox dynamically
        for (item in items) {
            val checkBox = CheckBox(requireContext())
            checkBox.text = item  // Set the label for the CheckBox

            // Add the CheckBox to the LinearLayout container
            checkBoxContainer.addView(checkBox)
        }

        if (popupView.parent != null) {
            (popupView.parent as? ViewGroup)?.removeView(popupView)
        }

        // Create the popup window
        val popupWindow = PopupWindow(
            popupView,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            true
        )


        popupWindow.setBackgroundDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.popup_background)
        )


        popupWindow.showAsDropDown(button)
    }
}