package com.example.booksapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.booksapp.R
import com.example.booksapp.adapter.BookListAdapter
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
            adapter = BookListAdapter(list = books){book ->
                val action = BookListFragmentDirections
                    .actionBookListFragmentToBookDetailFragment(
                        title = book.title,
                        author = book.author,
                        description = book.description
                    )
                findNavController().navigate(action)
            }
            binding.recyclerViewBooks.adapter = adapter
        }

        binding.addBook.setOnClickListener {
            findNavController().navigate(R.id.action_bookListFragment_to_addBookFragment)
        }

    }
}