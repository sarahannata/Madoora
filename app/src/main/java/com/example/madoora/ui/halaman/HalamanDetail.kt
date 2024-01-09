package com.example.madoora.ui.halaman

import com.example.madoora.R
import com.example.madoora.navigasi.DestinasiNavigasi

object DetailsDestination : DestinasiNavigasi {
    override val route = "item_details"
    override val titleRes = R.string.detail_admin
    const val adminIdArg = "itemId"
    val routeWithArgs = "$route/{$adminIdArg}"
}