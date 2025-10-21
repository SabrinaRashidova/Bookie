package com.example.booksapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.booksapp.R
import com.example.booksapp.databinding.FragmentBookDetailBinding


class BookDetailFragment : Fragment(R.layout.fragment_book_detail) {

    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!
    private val args: BookDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBookDetailBinding.bind(view)

        binding.tvTitle.text = args.title
        binding.tvAuthor.text = args.author
        binding.tvDescription.text = args.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}