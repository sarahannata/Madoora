package com.example.madoora.ui.halaman

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.madoora.Greeting
import com.example.madoora.ui.theme.MadooraTheme

@Composable
fun HalamanAwal(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Madoora",
            color = Color.Green, // Mengubah warna teks menjadi hijau
            fontSize = 24.sp, // Mengubah ukuran font
            fontWeight = FontWeight.Bold // Membuat teks menjadi tebal
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Membuat button dengan warna dan ukuran yang sama
        val buttonModifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = Color.Green)

        Button(
            modifier = buttonModifier,
            onClick = {
                    navController.navigate("EntryBarangScreen")
            }
        ) {
            Text(text = "Add Barang", color = Color.White) // Mengubah warna teks menjadi putih
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = buttonModifier,
            onClick = {
                navController.navigate("BarangHomeScreen")
            }
        ) {
            Text(text = "View Barang", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = buttonModifier,
            onClick = {
                navController.navigate("LoginPage")
            }
        ) {
            Text(text = "LogOut", color = Color.White)
        }
    }
}



