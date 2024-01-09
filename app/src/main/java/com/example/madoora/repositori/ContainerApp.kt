package com.example.madoora.repositori

import android.content.Context
import com.example.madoora.data.DatabaseMadoora

interface ContainerApp {
    val repositoriAdmin : RepositoriAdmin
    val repositoriBarang : RepositoriBarang

}

class ContainerDataApp(private val context: Context) : ContainerApp {
    private val database = DatabaseMadoora.getDatabase(context)

    override val repositoriAdmin : RepositoriAdmin by lazy {
        OfflineRepositoriMadoora(database.AdminDao(), database.BarangDao())
    }

    override val repositoriBarang: RepositoriBarang by lazy {
        OfflineRepositoriMadoora(database.AdminDao(), database.BarangDao())
    }
}