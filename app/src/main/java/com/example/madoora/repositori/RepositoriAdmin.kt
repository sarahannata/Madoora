package com.example.madoora.repositori

import com.example.madoora.data.Admin
import kotlinx.coroutines.flow.Flow

interface RepositoriAdmin {
    fun getAllAdminStream(): Flow<List<Admin>>

    fun getAdminStream(id : Int): Flow<Admin?>

    suspend fun insertAdmin(admin: Admin)

    suspend fun deleteAdmin(admin: Admin)

    suspend fun updateAdmin(admin: Admin)
}