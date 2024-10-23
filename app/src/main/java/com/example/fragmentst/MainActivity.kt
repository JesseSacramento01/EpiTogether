package com.example.fragmentst

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.example.fragmentst.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding object
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Set the content view using the binding's root

        // Handle window insets (e.g., for handling notches, status bar, etc.)
        ViewCompat.setOnApplyWindowInsetsListener(binding.drawerLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up the toolbar
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        // Initialize the DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = binding.navigationView

        // Set up the ActionBarDrawerToggle
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Set up navigation item selection
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_criarConta -> {
                    Toast.makeText(this, "Criar Conta Clicked", Toast.LENGTH_SHORT).show()
                    val navController = findNavController(R.id.nav_host_fragment)
                    // Navigate to the Fragment directly
                    navController.navigate(R.id.criarConta)
                }

                R.id.nav_login -> {
                    val navController = findNavController(R.id.nav_host_fragment)
                    navController.navigate(R.id.login)
                }

                R.id.nav_settings -> {
                    Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Set an OnClickListener on the button to open the fragment
        binding.homePage.setOnClickListener {
            val navController = findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.inicio)
        }

        binding.healthSection.setOnClickListener{
            val navController = findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.saude)
        }

        // Get references to the button container and the add button
        val buttonContainer: LinearLayout = binding.linearLayoutH
        val addButton: ImageButton = binding.newRegisterButton

        addButton.setOnClickListener {
            if (buttonContainer.visibility == View.GONE) {
                buttonContainer.visibility = View.VISIBLE
            } else {
                buttonContainer.visibility = View.GONE
            }
        }

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



