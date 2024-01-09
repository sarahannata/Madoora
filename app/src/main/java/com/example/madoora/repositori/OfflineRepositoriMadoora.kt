package com.example.madoora.repositori

import com.example.madoora.data.Admin
import com.example.madoora.data.AdminDao
import com.example.madoora.data.Barang
import com.example.madoora.data.BarangDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriMadoora(
    private val adminDao: AdminDao,
    private val barangDao: BarangDao
):RepositoriAdmin, RepositoriBarang {
    override fun getAllAdminStream(): Flow<List<Admin>> {
        return adminDao.getAllAdmin()
    }

    override fun getAdminStream(id: Int): Flow<Admin?> = adminDao.getAdmin(id)

    override suspend fun insertAdmin(admin: Admin) {
        adminDao.insertAdmin(admin)
    }

    override suspend fun deleteAdmin(admin: Admin) = adminDao.deleteAdmin(admin)

    override suspend fun updateAdmin(admin: Admin) = adminDao.updateAdmin(admin)

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