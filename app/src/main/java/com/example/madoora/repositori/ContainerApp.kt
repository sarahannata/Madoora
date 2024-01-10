package com.example.madoora.repositori

import android.content.Context
import com.example.madoora.data.DatabaseMadoora

interface ContainerApp {
    val repositoriBarang : RepositoriBarang

}
class ContainerDataApp(private val context: Context) : ContainerApp {

    override val repositoriBarang: RepositoriBarang by lazy {
        OfflineRepositoriMadoora(DatabaseMadoora.getDatabase(context).BarangDao())
    }
}