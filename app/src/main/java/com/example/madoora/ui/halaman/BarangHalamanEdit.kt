
package com.example.madoora.ui.halaman

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madoora.R
import com.example.madoora.model.BarangEditViewModel
import com.example.madoora.model.PenyediaViewModel
import com.example.madoora.navigasi.BarangTopAppBar
import com.example.madoora.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch

object BarangItemEditDestination : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_barang
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarangItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () ->Unit,
    modifier: Modifier = Modifier,
    viewModel: BarangEditViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    Scaffold (
        topBar = {
            BarangTopAppBar(
                title = stringResource(BarangDetailsDestination.titleRes),
                canNavigateBack =  true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ){ innerPadding ->
        EntryBarangBody(
            uiStateBarang = viewModel.barangUiState,
            onBarangValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateBarang()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}