package com.example.madoora.ui.halaman

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madoora.R
import com.example.madoora.model.BarangDetailsViewModel
import com.example.madoora.model.PenyediaViewModel
import com.example.madoora.navigasi.DestinasiNavigasi

object BarangDetailsDestination : DestinasiNavigasi {
    override val route = "item_details"
    override val titleRes = R.string.detail_barang
    const val barangIdArg = "itemId"
    val routeWithArgs = "$route/{$barangIdArg}"
}
@Composable
fun BarangDetailsScreen(
    navigateToEditItem: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BarangDetailsViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

}