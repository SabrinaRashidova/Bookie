package com.example.booksapp.data

import android.content.Context
import com.example.booksapp.R
import com.example.booksapp.data.model.BookModel

object FakeData {

    val list = mutableListOf<BookModel>()

    fun getItems(context: Context) : List<BookModel>{
        for (i in 0 .. 50){
            list.add(BookModel("The Great Gatsby", R.drawable.ic_info,"F. Scott Fitzgerald"))
        }

        return list
    }

}