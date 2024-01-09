package com.example.madoora.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblAdmin")
data class Admin(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val nama : String,
    val telpon : String
)