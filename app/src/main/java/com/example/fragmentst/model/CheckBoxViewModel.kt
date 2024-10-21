package com.example.fragmentst.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CheckBoxViewModel : ViewModel() {

    // Store the checkbox states for different buttons
    val checkBoxStatesMap: MutableLiveData<MutableMap<String, MutableList<Boolean>>> by lazy {
        MutableLiveData<MutableMap<String, MutableList<Boolean>>>().apply {
            value = mutableMapOf()
        }
    }

    // Initialize the states for a specific button
    fun initializeStates(buttonId: String, size: Int) {
        val statesMap = checkBoxStatesMap.value ?: mutableMapOf()
        if (!statesMap.containsKey(buttonId)) {
            statesMap[buttonId] = MutableList(size) { false } // Initially all checkboxes unchecked
            checkBoxStatesMap.value = statesMap
        }
    }

    // Update a specific checkbox state for a given button
    fun updateCheckBoxState(buttonId: String, index: Int, isChecked: Boolean) {
        val statesMap = checkBoxStatesMap.value
        statesMap?.let {
            val states = it[buttonId]
            states?.set(index, isChecked)
            checkBoxStatesMap.value = statesMap
        }
    }





}