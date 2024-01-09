package com.example.madoora.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Admin::class, Barang::class], version = 1, exportSchema = false)
abstract class DatabaseMadoora : RoomDatabase() {
    abstract fun AdminDao() : AdminDao
    abstract fun BarangDao() : BarangDao

    companion object{
        @Volatile
        private var Instance: DatabaseMadoora? = null

        fun getDatabase(context : Context): DatabaseMadoora{
            return (Instance?: synchronized(this){
                Room.databaseBuilder(context, DatabaseMadoora::class.java,"DatabaseMadoora")
                    .build().also { Instance=it }
            })
        }
    }
}