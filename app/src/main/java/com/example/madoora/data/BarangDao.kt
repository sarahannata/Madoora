package com.example.madoora.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BarangDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBarang(barang: Barang)

    @Update
    suspend fun updateBarang(barang: Barang)

    @Delete
    suspend fun deleteBarang(barang: Barang)

    @Query("SELECT * from tblBarang WHERE id = :id")
    fun getBarang (id: Int): Flow<Barang>

    @Query("SELECT * from tblBarang ORDER BY namaBarang ASC")
    fun getAllBarang (): Flow<List<Barang>>
}