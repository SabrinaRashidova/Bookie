package com.example.booksapp.ui.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.booksapp.R
import com.example.booksapp.databinding.FragmentRegisterBinding
import com.example.booksapp.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterBinding.bind(view)

        binding.btnSubmit.setOnClickListener {
            val login = binding.etLogin.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()

            if (login.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                Snackbar.make(binding.root,"Please fill in all fields", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword){
                Snackbar.make(binding.root,"Passwords do not match", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewmodel.registerUser(login,password){ success->
                if (success){
                    Snackbar.make(binding.root,"Registered Successfully!", Snackbar.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registerFragment_to_bookListFragment)
                }else{
                    Snackbar.make(binding.root,"Registration failed!", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}