package com.example.fragmentst.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.example.fragmentst.R
import com.example.fragmentst.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding object
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Set the content view using the binding's root

        // Handle window insets (e.g., for handling notches, status bar, etc.)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Set an OnClickListener on the button to open the fragment
            binding.homePage.setOnClickListener {
                val navController = findNavController(R.id.nav_host_fragment)
                // Navigate to the Fragment directly
                navController.navigate(R.id.inicio)
                }

            binding.healthSection.setOnClickListener{
                val navController = findNavController(R.id.nav_host_fragment)
                // Navigate to the Fragment directly
                navController.navigate(R.id.saude)
            }

        // Get references to the button container and the add button
        val buttonContainer: LinearLayout = binding.linearLayoutH
        val addButton: ImageButton = binding.newRegisterButton

        // Set an onClickListener on the Add button
        addButton.setOnClickListener {
            // Toggle the visibility of the button container
            if (buttonContainer.visibility == View.GONE) {
                buttonContainer.visibility = View.VISIBLE
            } else {
                buttonContainer.visibility = View.GONE
            }
        }

        // Optionally, handle click events on the two buttons
        val registerSignsButton: Button = binding.registerSignsButton
        val registerCrisisButton: Button = binding.buttonCrise
        registerSignsButton.setOnClickListener {

        }

        registerCrisisButton.setOnClickListener {
            val navController = findNavController(R.id.nav_host_fragment)
            // Navigate to the Fragment directly
            navController.navigate(R.id.registarCrise)
            buttonContainer.visibility = View.GONE // Make sure it disappears once the option is chosen
        }
    }
}

