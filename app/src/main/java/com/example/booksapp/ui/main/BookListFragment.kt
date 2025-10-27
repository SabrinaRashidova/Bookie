package com.example.booksapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.R
import com.example.booksapp.adapter.BookListAdapter
import com.example.booksapp.databinding.FragmentBookListBinding
import com.example.booksapp.viewmodel.BookViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class BookListFragment : Fragment(R.layout.fragment_book_list) {

    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: BookListAdapter
    private val viewmodel by viewModels<BookViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBookListBinding.bind(view)

        viewmodel.allBooks.observe(viewLifecycleOwner){books ->
            if (books.isEmpty()){
                binding.ivEmptyList.visibility = View.VISIBLE
                binding.txtEmpty.visibility = View.VISIBLE
                binding.recyclerViewBooks.visibility = View.GONE
            }else{
                binding.ivEmptyList.visibility = View.GONE
                binding.txtEmpty.visibility = View.GONE
                binding.recyclerViewBooks.visibility = View.VISIBLE
            }
            adapter = BookListAdapter(list = books){book ->
                val action = BookListFragmentDirections
                    .actionBookListFragmentToBookDetailFragment(
                        title = book.title,
                        author = book.author,
                        description = book.description
                    )
                findNavController().navigate(action)
            }
            viewLifecycleOwner.lifecycleScope.launch {
                binding.recyclerViewBooks.adapter = adapter
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
            }
        }

        binding.addBook.setOnClickListener {
            findNavController().navigate(R.id.action_bookListFragment_to_addBookFragment)
        }

        setupSwipeToDelete()
    }

    private fun setupSwipeToDelete(){
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
                val position = viewHolder.adapterPosition
                val book = viewmodel.allBooks.value?.get(position)

                if (book != null){
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Delete Book")
                        .setMessage("Are you sure you want to delete \"${book.title}\"?")
                        .setPositiveButton("Yes") { _, _ ->
                            viewmodel.delete(book)
                        }
                        .setNegativeButton("No") { dialog, _ ->
                            dialog.dismiss()
                            adapter.notifyItemChanged(position)
                        }
                        .setCancelable(false)
                        .show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.recyclerViewBooks)
    }
}