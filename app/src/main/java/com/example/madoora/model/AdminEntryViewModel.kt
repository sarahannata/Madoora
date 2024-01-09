package com.example.madoora.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.madoora.data.Admin
import com.example.madoora.repositori.RepositoriAdmin

class EntryViewModel (private val repositoriAdmin: RepositoriAdmin): ViewModel(){

    var uiStateAdmin by mutableStateOf(UIStateAdmin())
        private set

    /* Fungsi untuk memvalidasi input*/
    private fun validasiInput(uiState: DetailAdmin = uiStateAdmin.detailAdmin ): Boolean{
        return with(uiState){
            nama.isNotBlank()&& telpon.isNotBlank()
        }
    }

    fun updateUiState(detailAdmin: DetailAdmin){
        uiStateAdmin =
            UIStateAdmin(detailAdmin = detailAdmin, isEntryValid = validasiInput(detailAdmin))
    }
    /* Fungsi untuk menyimpan data yang di-entry */
    suspend fun saveAdmin(){
        if (validasiInput()){
            repositoriAdmin.insertAdmin(uiStateAdmin.detailAdmin.toAdmin())
        }
    }
}
data class UIStateAdmin(
    val detailAdmin: DetailAdmin = DetailAdmin(),
    val isEntryValid: Boolean = false
)

data class DetailAdmin(
    val id: Int = 0,
    val nama: String = "",
    val telpon: String = "",
)
/* Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis data*/
fun DetailAdmin.toAdmin(): Admin = Admin(
    id = id,
    nama = nama,
    telpon = telpon
)

fun Admin.toUiStateAdmin(isEntryValid: Boolean = false): UIStateAdmin = UIStateAdmin(
    detailAdmin = this.toDetailAdmin(),
    isEntryValid = isEntryValid
)
fun Admin.toDetailAdmin(): DetailAdmin = com.example.madoora.model.DetailAdmin(
    id = id,
    nama = nama,
    telpon = telpon
)