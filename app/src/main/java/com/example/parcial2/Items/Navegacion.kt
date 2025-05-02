package com.example.parcial2.Items

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
@Preview(showBackground = true)
fun Navegacion(){
    val navController = rememberNavController()
    val userList = remember { mutableStateListOf<Producto>() }
    val carro = remember { mutableStateListOf<Producto>() }


    NavHost(navController = navController, startDestination = "screenA") {
        composable("screenA") {
            PantallaCatalogo(navController, userList, carro)
        }
        composable("screenB") {
            PantallaRegistro(navController, userList)
        }
        composable("screenC/{productoId}") { backStackEntry ->
            val productoId = backStackEntry.arguments?.getString("productoId")?.toIntOrNull()
            val producto = userList.find { it.id == productoId }

            if (producto != null) {
                PantallaDetalle(navController, producto, carro)
            } else {

                Text("Producto no encontrado")
            }
        }

        composable("screenD"){
            PantallaCarrito(navController, carro)
        }
    }
}