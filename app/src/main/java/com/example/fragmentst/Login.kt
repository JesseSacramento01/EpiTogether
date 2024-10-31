package com.example.fragmentst

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fragmentst.databinding.FragmentLoginBinding
import com.example.fragmentst.model.SharedViewModel
import com.example.fragmentst.model.UtilizadorViewModel
import com.example.fragmentst.model.UtilizadorViewModelFactory
import com.example.fragmentst.repository.UtilizadorRepository
import kotlinx.coroutines.launch


class Login : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private lateinit var utilizadorViewModel: UtilizadorViewModel
    private val sharedViewModel : SharedViewModel by activityViewModels()
    private var sucessfulLogin = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)


        // assign the value for the utilizadorViewModel
        val application = requireActivity().application

        val repository = UtilizadorRepository(application)

        val factory = UtilizadorViewModelFactory(application, repository)
        utilizadorViewModel = ViewModelProvider(this, factory)[UtilizadorViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            // Collect the Flow in a lifecycle-aware way
            viewLifecycleOwner.lifecycleScope.launch {
                utilizadorViewModel.utilizadorListFlow.collect { utilizadores ->

                    for (utilizador in utilizadores) {
                        if (binding.etUsername.text.toString() == utilizador.nome
                            && binding.etPassword.text.toString() == utilizador.password
                        ) {
                            sharedViewModel.idUtilizador = utilizador.idUtilizador

                            val dialogBuilder = AlertDialog.Builder(requireContext())
                                .setTitle("Login")
                                .setMessage("Login efectuado com sucesso! ")
                                dialogBuilder.create().show()

                            sucessfulLogin = true

                            findNavController().navigate(R.id.action_login_to_inicio)
                        }
                        else{
                            sucessfulLogin = false
                        }
                    }
                }
            }
            if( !sucessfulLogin ) {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                    .setTitle("Login")
                    .setMessage("Dados de login incorretos! ")
                dialogBuilder.create().show()
            }
        }

    }

}
