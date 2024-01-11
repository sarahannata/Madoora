package com.example.madoora.model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.madoora.AplikasiBarang

object PenyediaViewModel {
    val Factory = viewModelFactory{
        initializer{
            BarangHomeViewModel(aplikasiBarang().container.repositoriBarang)
        }
        initializer{
            BarangEntryViewModel(aplikasiBarang().container.repositoriBarang)
        }
        initializer { BarangDetailsViewModel(
            createSavedStateHandle(),aplikasiBarang().container.repositoriBarang
        ) }
        initializer { BarangEditViewModel(
            createSavedStateHandle(),aplikasiBarang().container.repositoriBarang
        ) }
    }
    /**
     * Fungsi ekstensi query untuk object [Application] dan mengembalikan sebuah instance dari [AplikasiSiswa].
     */

    fun CreationExtras.aplikasiBarang(): AplikasiBarang =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiBarang)
}
