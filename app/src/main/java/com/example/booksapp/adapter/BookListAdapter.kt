package com.example.booksapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.data.Book
import com.example.booksapp.databinding.BookItemBinding

class BookListAdapter(private val list: List<Book>): RecyclerView.Adapter<BookListAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(BookItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int  = list.size

    inner class ViewHolder(private val binding: BookItemBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(book: Book){
            binding.txtTitle.text = book.title
            binding.txtAuthor.text = book.author
        }
    }

}