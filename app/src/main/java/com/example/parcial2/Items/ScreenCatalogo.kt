package com.example.parcial2.Items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaCatalogo(navController: NavController, userList: MutableList<Map<String, String>>) {
    val cardColors = listOf(
        Color(0xFFF7B1CE),
        Color(0xFFA5D6A7),
        Color(0xFFFFF59D),
        Color(0xFF81D4FA),
        Color(0xFFD1C4E9),
        Color(0xFFFFCCBC)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFCE4EC),
                        Color(0xFFB3E5FC),
                        Color(0xFFC8E6C9)
                    )
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Catálogo de Productos",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (userList.isEmpty()) {
                Text("No hay productos registrados", color = Color.DarkGray)
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 16.dp)
                ) {
                    itemsIndexed(userList) { index, user ->
                        val color = cardColors[index % cardColors.size]

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = color),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Nombre:${user["nombre"]} ")
                                Text("Precio: ${user["precio"]}")
                                Text("ImagenUrl:  ${user["imagenUrl"]}")
                            }
                        }
                    }
                }

            }
        }
        Button(
            onClick = {
            navController.navigate("screenB")
     },
    modifier = Modifier.fillMaxWidth()
    ) {
        Text("Agregar nuevo Producto ")
        }
    }
}
