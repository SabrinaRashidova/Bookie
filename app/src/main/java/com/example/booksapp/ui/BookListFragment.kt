package com.example.booksapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.booksapp.R
import com.example.booksapp.adapter.BookListAdapter
import com.example.booksapp.data.FakeData
import com.example.booksapp.databinding.FragmentBookListBinding

class BookListFragment : Fragment(R.layout.fragment_book_list) {

    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: BookListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBookListBinding.bind(view)

        adapter = BookListAdapter(FakeData.getItems(requireContext()))
        binding.recyclerViewBooks.adapter = adapter
    }
}