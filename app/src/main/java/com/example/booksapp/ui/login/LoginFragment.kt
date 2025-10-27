package com.example.booksapp.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.booksapp.R
import com.example.booksapp.databinding.FragmentLoginBinding
import com.example.booksapp.viewmodel.BookViewModel
import com.example.booksapp.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlin.math.log

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        binding.btnLogin.setOnClickListener {
            val login = binding.etLogin.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (login.isEmpty() || password.isEmpty()){
                Snackbar.make(binding.root,"Please fill in all fields", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewmodel.loginUser(login,password){ success->
                if (success){
                    Snackbar.make(binding.root,"Logged in Successfully!", Snackbar.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_bookListFragment)
                }
            }
        }

        binding.txtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}