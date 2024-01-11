package com.example.madoora.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madoora.data.Barang
import com.example.madoora.repositori.RepositoriBarang
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BarangHomeViewModel(
    private val repositoriBarang: RepositoriBarang):ViewModel(){
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> = repositoriBarang.getAllBarangStream()
        .filterNotNull()
        .map{ HomeUiState(listBarang = it.toList())}
        .stateIn(scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState())

    data class HomeUiState(
        val listBarang: List<Barang> = listOf()
    )
}

