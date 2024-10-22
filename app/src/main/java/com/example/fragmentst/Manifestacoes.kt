package com.example.fragmentst

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.fragmentst.databinding.FragmentManifestacoesBinding
import com.example.fragmentst.model.CheckBoxViewModel


class Manifestacoes : Fragment() {


    private lateinit var binding: FragmentManifestacoesBinding
    private val checkBoxViewModel: CheckBoxViewModel by activityViewModels()
    private var currentPopupWindow: PopupWindow? = null
    private var currentCheckBoxOption: String? = null

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
            items = listOf("Tremores", "Espasmos Musculares", "Rígidez", "Perda de Força Muscular", "Ausência de Movimento")
            createDropdownMenu(moveButton.id,items, moveButton)

            // to retrieve the data from the manifestations section form
            checkBoxViewModel.tipoMovimento = currentCheckBoxOption
        }

        localizacaoMovimento.setOnClickListener {
            items = listOf("Generalizados (todo o corpo)",
                    "Focais (uma parte do corpo: mão, braço, perna ou face)",
                    "Não se aplica",
                    "Espasmos Musculares", "Rígidez", "Perda de Força Muscular", "Ausência de Movimento")

            createDropdownMenu(localizacaoMovimento.id,items, localizacaoMovimento)

            checkBoxViewModel.localizacaoMovimento = currentCheckBoxOption
        }

        binding.buttonCorDaPele.setOnClickListener {
            items = listOf("Palidez","Cianose (pele azulada)", "Vermelhidão", "Outra", "Sem alteração")
            createDropdownMenu(binding.buttonCorDaPele.id,items, binding.buttonCorDaPele)
            checkBoxViewModel.corDaPele = currentCheckBoxOption
        }

        binding.buttonRespiraO.setOnClickListener {
            items = listOf("Apneia (Ausência de Respiração)", "Respiração irregular ou ofegante", "Outra", "Sem alterações")
            createDropdownMenu(binding.buttonRespiraO.id,items, binding.buttonRespiraO)
            checkBoxViewModel.respiracao = currentCheckBoxOption
        }

        binding.buttonEstadoDeConciencia.setOnClickListener {
            items = listOf("Consciente", "Inconsciente", "Sem alteração")
            createDropdownMenu(binding.buttonEstadoDeConciencia.id,items, binding.buttonEstadoDeConciencia)
            checkBoxViewModel.estadoDeConciencia = currentCheckBoxOption
        }

        binding.buttonOutrasManifestacoes.setOnClickListener {
            items = listOf("Movimentos de mastigação", "Salivação excessiva", "Alucinações visuais ou auditivas", "Outras")
            createDropdownMenu(binding.buttonOutrasManifestacoes.id,items, binding.buttonOutrasManifestacoes)
            checkBoxViewModel.outrasManifestacoes = currentCheckBoxOption
        }

        binding.buttonSalvar.setOnClickListener {
            //TODO Process to save the data
            findNavController().navigate(R.id.action_manifestacoes2_to_registarCrise)
        }

    }

    private fun createDropdownMenu(id: Int, items:List<String>, button:Button ){
        // Dismiss the currently displayed popup if it exists
        currentPopupWindow?.dismiss()

        // Inflate the popup layout
        val inflater: LayoutInflater = layoutInflater
        val popupView = inflater.inflate(R.layout.window_popup, null)
        val buttonID : String = resources.getResourceEntryName(id)


        val checkBoxContainer: LinearLayout = popupView.findViewById(R.id.checkboxContainer)

        // Initialize the ViewModel with the number of checkboxes for this specific button
        checkBoxViewModel.initializeStates(buttonID, items.size)


        // Observe the checkbox states for this button
        checkBoxViewModel.checkBoxStatesMap.observe(viewLifecycleOwner, Observer { statesMap ->
            val states = statesMap[buttonID] ?: return@Observer

            // Remove any existing checkboxes in case this is a recreation
            checkBoxContainer.removeAllViews()


            // Loop through each item and create a CheckBox dynamically
            for ((index, item) in items.withIndex()) {
                val checkBox = CheckBox(requireContext())
                checkBox.text = item  // Set the label for the CheckBox

                // Add the CheckBox to the LinearLayout container
                checkBoxContainer.addView(checkBox)

                // Set the checked state from ViewModel
                checkBox.isChecked = states[index]

                // Set an OnCheckedChangeListener to handle the click event
                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    checkBoxViewModel.updateCheckBoxState(buttonID,index, isChecked)
                    if (isChecked) {

                        currentCheckBoxOption = checkBox.text.toString()
                        // Handle the checkbox being checked
                        Toast.makeText(
                            requireContext(),
                            "${checkBox.text} selected",
                            Toast.LENGTH_SHORT

                        ).show()
                    } else {

                        currentCheckBoxOption = checkBox.text.toString()
                        // Handle the checkbox being unchecked
                        Toast.makeText(
                            requireContext(),
                            "${checkBox.text} deselected",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }


            if (popupView.parent != null) {
                (popupView.parent as? ViewGroup)?.removeView(popupView)
            }

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
            currentPopupWindow = popupWindow

            // Dismiss the popup when it's clicked outside
            popupWindow.setOnDismissListener {
                currentPopupWindow = null // Clear the reference when dismissed
            }
        })
    }
}