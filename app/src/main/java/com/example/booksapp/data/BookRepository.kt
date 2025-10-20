package com.example.booksapp.data

import androidx.lifecycle.LiveData

class BookRepository(private val dao: BookDao) {
    val allBooks: LiveData<List<Book>> = dao.getAllBooks()

    suspend fun insert(book: Book) = dao.insertBook(book)
    suspend fun delete(book: Book) = dao.deleteBook(book)
}