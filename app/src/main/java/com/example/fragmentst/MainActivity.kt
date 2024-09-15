package com.example.fragmentst

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
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
                // Navigate to the FirstFragment directly
                navController.navigate(R.id.inicio)
                }

            }

        }
