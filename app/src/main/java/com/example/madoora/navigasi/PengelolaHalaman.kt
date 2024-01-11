package com.example.madoora.navigasi

import LoginPage
import RegisterPage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.madoora.R
import com.example.madoora.ui.halaman.BarangDetailsDestination
import com.example.madoora.ui.halaman.BarangDetailsScreen
import com.example.madoora.ui.halaman.BarangHomeScreen
import com.example.madoora.ui.halaman.BarangItemEditDestination
import com.example.madoora.ui.halaman.BarangItemEditScreen
import com.example.madoora.ui.halaman.DestinasiEntry
import com.example.madoora.ui.halaman.EntryBarangScreen
import com.example.madoora.ui.halaman.HalamanAwal


@Composable
fun BarangApp(navController: NavHostController = rememberNavController()){
    var isUserLoggedIn by remember { mutableStateOf(false) }
    HostNavigasi(navController = navController, isUserLoggedIn)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarangTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
){
    CenterAlignedTopAppBar(title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                        id = R.string.back)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavController,
    isUserLoggedIn: Boolean,
    modifier: Modifier = Modifier
){
    NavHost(navController = navController as NavHostController, startDestination = "RegisterPage", modifier = Modifier)
    {
        composable("LoginPage"){
            LoginPage(
                navController = navController,
                navigateLogin = { navController.popBackStack() }
            )
        }
        composable("BarangHomeScreen") {
            BarangHomeScreen(
                navController = navController,
                navigateBack = { navController.popBackStack() },
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route)},
                modifier = Modifier ,
            )
        }
        composable("RegisterPage"){
            RegisterPage(
                navController = navController
            )
        }
        composable("HalamanAwal"){
            HalamanAwal(
                navController = navController
            )
        }




        composable("EntryBarangScreen"){
            EntryBarangScreen(navigateBack = { navController.popBackStack() },
                navController = navController
            )
        }
        composable(
            BarangDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(BarangDetailsDestination.barangIdArg){
                type = NavType.IntType
            })
        ){
            BarangDetailsScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEditItem = {
                    navController.navigate("${BarangItemEditDestination.route}/$it")

                }
            )
        }
        composable(
            BarangDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(BarangItemEditDestination.itemIdArg){
                type = NavType.IntType
            })
        ){
            BarangItemEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
    }
}
