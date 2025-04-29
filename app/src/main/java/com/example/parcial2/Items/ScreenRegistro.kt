package com.example.parcial2.Items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun PantallaRegistro(navController: NavController, userList: MutableList<Map<String, String>>) {
    var nombreProducto by remember {mutableStateOf("") }
    val precioProducto by remember { mutableStateOf("")}
    var descripcionProducto by remember {mutableStateOf("") }
    var imagenUrlProducto by remember {mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Agregar Producto", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold))

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombreProducto,
            onValueChange = {
                nombreProducto = it
            },
            label = { Text("Nombre Del Producto") },

        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = precioProducto,
            onValueChange = {
                precioProducto
            },
            label = { Text("Precio Del Producto") }, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number))

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = descripcionProducto,
            onValueChange = {
                descripcionProducto = it
            },
            label = { Text("Descripcion Del Producto") },
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = imagenUrlProducto,
            onValueChange = {
                imagenUrlProducto = it
            },
            label = { Text("Imagen Del Producto") },
        )
        Spacer(modifier = Modifier.height(16.dp))

       Button( {

        if (nombreProducto.isBlank() && precioProducto.isBlank() && descripcionProducto.isBlank() && imagenUrlProducto.isBlank())
            {
            userList.add(
                mapOf(
                    "nombre" to nombreProducto,
                    "precio" to precioProducto,
                    "descripcion" to descripcionProducto,
                    "imagen" to imagenUrlProducto
                )
            )
            navController.navigate("screenB")
        }
    },
    modifier = Modifier.fillMaxWidth()
    ) {
        Text("Agregar")
    }
    Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                navController.navigate("screenA")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al catalogo ")
        }

    }
}
