package com.example.madoora.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblBarang")
data class Barang (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val namaBarang : String,
    val jumlah : String,
    val harga : String
)