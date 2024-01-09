package com.example.madoora.ui.halaman

import com.example.madoora.R
import com.example.madoora.navigasi.DestinasiNavigasi

object BarangDetailsDestination : DestinasiNavigasi {
    override val route = "item_details"
    override val titleRes = R.string.detail_barang
    const val barangIdArg = "itemId"
    val routeWithArgs = "$route/{$barangIdArg}"
}