package com.example.fragmentst.viewmodel

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
        var date: String? = null
        var time: String? = null
        var duration: String? = null
        var locationSpinner: String? = null
        var cycleSpinner : String? = null
        var activitySpinner: String? = null
        var idUtilizador: Int = 0
}