package com.example.booksapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.booksapp.data.book.Book
import com.example.booksapp.data.book.BookDao
import com.example.booksapp.data.user.User
import com.example.booksapp.data.user.UserDao

@Database(entities = [Book::class, User::class], version = 2, exportSchema = false)
abstract class BookDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun userDao(): UserDao

    companion object{
        private var INSTANCE: BookDatabase? = null
        fun getDatabase(context: Context) : BookDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    BookDatabase::class.java,
                    "book_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE!!
        }
    }
}