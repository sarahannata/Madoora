package com.example.madoora.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.madoora.data.Barang
import com.example.madoora.repositori.RepositoriBarang

class BarangEntryViewModel (private val repositoriBarang: RepositoriBarang): ViewModel(){

    var uiStateBarang by mutableStateOf(UIStateBarang())
        private set

    /* Fungsi untuk memvalidasi input*/
    private fun validasiInput(uiState: DetailBarang = uiStateBarang.detailBarang ): Boolean{
        return with(uiState){
            namaBarang.isNotBlank()&& harga.isNotBlank()
        }
    }

    fun updateUiState(detailBarang: DetailBarang){
        uiStateBarang =
            UIStateBarang(detailBarang = detailBarang, isEntryValid = validasiInput(detailBarang))
    }
    /* Fungsi untuk menyimpan data yang di-entry */
    suspend fun saveBarang(){
        if (validasiInput()){
            repositoriBarang.insertBarang(uiStateBarang.detailBarang.toBarang())
        }
    }
}
data class UIStateBarang(
    val detailBarang: DetailBarang = DetailBarang(),
    val isEntryValid: Boolean = false
)

data class DetailBarang(
    val id : Int = 0,
    val namaBarang : String = "",
    val jumlah : String="",
    val harga : String=""
)
/* Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis data*/
fun DetailBarang.toBarang(): Barang = Barang(
    id = id,
    namaBarang = namaBarang,
    jumlah = jumlah,
    harga = harga,
)

fun Barang.toUiStateBarang(isEntryValid: Boolean = false): UIStateBarang = UIStateBarang(
    detailBarang = this.toDetailBarang(),
    isEntryValid = isEntryValid
)
fun Barang.toDetailBarang(): DetailBarang = com.example.madoora.model.DetailBarang(
    id = id,
    namaBarang = namaBarang,
    jumlah = jumlah,
    harga = harga,
)