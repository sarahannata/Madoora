package com.example.madoora.repositori

import com.example.madoora.data.Barang
import kotlinx.coroutines.flow.Flow

interface RepositoriBarang {
    fun getAllBarangStream(): Flow<List<Barang>>

    fun getBarangStream(id : Int): Flow<Barang?>

    suspend fun insertBarang(barang: Barang)

    suspend fun deleteBarang(barang: Barang)

    suspend fun updateBarang(barang: Barang)
}