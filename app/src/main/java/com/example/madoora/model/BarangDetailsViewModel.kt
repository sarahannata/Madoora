package com.example.madoora.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madoora.repositori.RepositoriBarang
import com.example.madoora.ui.halaman.BarangDetailsDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BarangDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriBarang: RepositoriBarang
) : ViewModel(){
    private val barangId: Int = checkNotNull(savedStateHandle[BarangDetailsDestination.barangIdArg])
    val uiState: StateFlow<BarangItemDetailsUiState> =
        repositoriBarang.getBarangStream(barangId).filterNotNull().map { BarangItemDetailsUiState(detailBarang = it.toDetailBarang())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = BarangItemDetailsUiState()
        )
    suspend fun deleteItem(){
        repositoriBarang.deleteBarang(uiState.value.detailBarang.toBarang())
    }
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
data class BarangItemDetailsUiState(
    val outOfStock: Boolean = true,
    val detailBarang: DetailBarang = DetailBarang(),
)