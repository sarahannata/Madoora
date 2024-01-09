package com.example.madoora.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madoora.repositori.RepositoriAdmin
import com.example.madoora.ui.halaman.DetailsDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AdminDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriAdmin: RepositoriAdmin
) : ViewModel(){
    private val adminId: Int = checkNotNull(savedStateHandle[DetailsDestination.adminIdArg])
    val uiState: StateFlow<ItemDetailsUiState> =
        repositoriAdmin.getAdminStream(adminId).filterNotNull().map { ItemDetailsUiState(detailAdmin = it.toDetailAdmin())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ItemDetailsUiState()
        )
    suspend fun deleteItem(){
        repositoriAdmin.deleteAdmin(uiState.value.detailAdmin.toAdmin())
    }
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val detailAdmin: DetailAdmin = DetailAdmin(),
)