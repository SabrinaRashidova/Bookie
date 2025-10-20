package com.example.booksapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.booksapp.R
import com.example.booksapp.adapter.BookListAdapter
import com.example.booksapp.data.Book
import com.example.booksapp.databinding.DialogAddBookBinding
import com.example.booksapp.databinding.FragmentBookListBinding
import com.example.booksapp.viewmodel.BookViewModel

class BookListFragment : Fragment(R.layout.fragment_book_list) {

    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: BookListAdapter
    private val viewmodel by viewModels<BookViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBookListBinding.bind(view)

        viewmodel.allBooks.observe(viewLifecycleOwner){books ->
            adapter = BookListAdapter(list = books)
            binding.recyclerViewBooks.adapter = adapter
        }

        binding.addBook.setOnClickListener {
            showAddDialog()
        }

    }

    private fun showAddDialog() {
        val dialogBinding = DialogAddBookBinding.inflate(LayoutInflater.from(requireContext()))

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add new book")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") { dialogInterface, _ ->
                val title = dialogBinding.etTitle.text.toString().trim()
                val author = dialogBinding.etAuthor.text.toString().trim()

                if (title.isNotEmpty() && author.isNotEmpty()) {
                    val book = Book(0,title = title, author = author)
                    viewmodel.insert(book)
                }
                dialogInterface.dismiss()
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()

    }
}