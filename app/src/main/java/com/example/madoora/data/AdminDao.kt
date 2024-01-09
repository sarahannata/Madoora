package com.example.madoora.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AdminDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAdmin(admin: Admin)

    @Update
    suspend fun updateAdmin(admin: Admin)

    @Delete
    suspend fun deleteAdmin(admin: Admin)

    @Query("SELECT * from tblAdmin WHERE id = :id")
    fun getAdmin(id: Int): Flow<Admin>

    @Query("SELECT * from tblAdmin ORDER BY nama ASC")
    fun getAllAdmin(): Flow<List<Admin>>

}