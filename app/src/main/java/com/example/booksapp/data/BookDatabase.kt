package com.example.booksapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object{
        private var INSTANCE: BookDatabase? = null
        fun getDatabase(context: Context) : BookDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    BookDatabase::class.java,
                    "book_database"
                ).build()
            }

            return INSTANCE!!
        }
    }
}