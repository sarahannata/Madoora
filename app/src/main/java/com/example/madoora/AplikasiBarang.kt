package com.example.madoora

import android.app.Application
import com.example.madoora.repositori.ContainerApp
import com.example.madoora.repositori.ContainerDataApp

class AplikasiBarang : Application() {
    /**
     * AppContainer instance digunakan oleh kelas-kelas lainnya untuk mendapatkan dependensi
     */
    lateinit var container: ContainerApp

    override fun onCreate(){
        super.onCreate()
        container = ContainerDataApp(this)
    }
}