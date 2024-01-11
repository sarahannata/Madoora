package com.example.madoora.navigasi

import com.example.madoora.R

interface DestinasiNavigasi {
    val route: String
    val titleRes: Int
}
object DestinasiView : DestinasiNavigasi {
    override val route = "view"
    override val titleRes = R.string.view_title
}
