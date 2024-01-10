package com.example.madoora.repositori

import com.example.madoora.data.Barang
import com.example.madoora.data.BarangDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriMadoora(

    private val barangDao: BarangDao): RepositoriBarang {
    override fun getAllBarangStream(): Flow<List<Barang>> {
        return barangDao.getAllBarang()
    }

    override fun getBarangStream(id: Int): Flow<Barang?> = barangDao.getBarang(id)

    override suspend fun insertBarang(barang: Barang) {
        barangDao.insertBarang(barang)
    }

    override suspend fun deleteBarang(barang: Barang) = barangDao.deleteBarang(barang)

    override suspend fun updateBarang(barang: Barang) = barangDao.updateBarang(barang)

}