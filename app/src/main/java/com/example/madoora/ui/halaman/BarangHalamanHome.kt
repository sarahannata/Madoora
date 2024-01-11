package com.example.madoora.ui.halaman

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.madoora.R
import com.example.madoora.data.Barang
import com.example.madoora.model.BarangHomeViewModel
import com.example.madoora.model.DetailBarang
import com.example.madoora.model.ItemDeleteUiState
import com.example.madoora.model.PenyediaViewModel
import com.example.madoora.navigasi.BarangTopAppBar
import com.example.madoora.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarangHomeScreen(
    navigateToItemEntry: () -> Unit,
    navController: NavHostController,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    onBackClicked: () -> Unit = {},
    viewModel: BarangHomeViewModel = viewModel(factory = PenyediaViewModel.Factory),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            BarangTopAppBar(
                title = "VIEW",
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = {
                    navController.navigate("HalamanAwal")
                }
            )
        },

    ) { innerPadding ->
        val uiStateBarang by viewModel.homeUiState.collectAsState()
        BodyHome(
            itemBarang = uiStateBarang.listBarang,
            passingLagi = { viewModel.homeUiState.value },
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onBarangClick = onDetailClick
        )
    }
}

@Composable
fun BodyHome(
    itemBarang: List<Barang>,
    modifier: Modifier = Modifier,
    onBarangClick: (Int) -> Unit = {},
    passingLagi:()->Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,

        ) {
        if (itemBarang.isEmpty()) {
            Text(
                text = stringResource(id = R.string.detail_barang),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListBarang(
                itemBarang = itemBarang,
                onItemClick = {
                    onBarangClick(it.id)
                },
                passDelete = passingLagi
            )
        }
    }
}

@Composable
fun ListBarang(
    itemBarang: List<Barang>,
    modifier: Modifier = Modifier,
    onItemClick: (Barang) -> Unit,
    passDelete:()->Unit
) {
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(modifier = Modifier) {
        items(items = itemBarang, key = { it.id }) { person ->
            DataBarang(

                barang = person,
                onDelete = passDelete,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(person) },
            )
        }
    }
}

@Composable
fun DataBarang(
    barang: Barang,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit

) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {

            var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = barang.namaBarang,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                )
                Text(
                    text = barang.jumlah,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row (modifier = Modifier.fillMaxWidth(),){
                Text(
                    text = barang.harga,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {
                    deleteConfirmationRequired = true
                    onDelete()
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
            }
            if (deleteConfirmationRequired){
                DeleteConfirmationDialog(
                    onDeleteConfirm = {
                        deleteConfirmationRequired = false
                        onDelete() },
                    onDeleteCancel = { deleteConfirmationRequired = false },
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
            }
        }
    }
}

@Composable
private fun ItemDeleteBody(
    itemDeleteUiState: ItemDeleteUiState,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ){
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
        OutlinedButton(
            onClick = {deleteConfirmationRequired = true},
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete")
        }
        if (deleteConfirmationRequired){
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDelete() },
                onDeleteCancel = {deleteConfirmationRequired = false},
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {  },
        title = { Text(text = stringResource(id = R.string.attention))},
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = stringResource(id = R.string.no))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(id = R.string.yes))
            }
        })
}