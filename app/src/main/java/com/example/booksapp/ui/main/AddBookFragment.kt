package com.example.booksapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.booksapp.R
import com.example.booksapp.data.Book
import com.example.booksapp.databinding.FragmentAddBookBinding
import com.example.booksapp.viewmodel.BookViewModel
import com.google.android.material.snackbar.Snackbar

class AddBookFragment : Fragment(R.layout.fragment_add_book) {

    private var _binding: FragmentAddBookBinding? = null
    private val binding get() = _binding!!
    private val viewmodel by viewModels<BookViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddBookBinding.bind(view)

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString().trim().uppercase()
            val author = binding.etAuthor.text.toString().trim().uppercase()
            val desc = binding.etDescription.text.toString().trim()

            if (title.isNotBlank() && author.isNotBlank() && desc.isNotBlank()){
                val book = Book(0,title = title, author = author,desc)
                viewmodel.insert(book)
                Snackbar.make(binding.root,"Book added successfully",Snackbar.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }else{
                Snackbar.make(binding.root,"Please fill all fields", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}