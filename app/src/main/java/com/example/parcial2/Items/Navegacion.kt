package com.example.parcial2.Items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val userList = rememberSaveable(saver = listSaver(
        save = { it.map { user -> user.toMap() } },
        restore = { it.map { item -> item as Map<String, String> }.toMutableStateList() }
    )) {
        mutableStateListOf<Map<String, String>>()
    }

    NavHost(navController = navController, startDestination = "screenA") {
        composable("screenA") {
            PantallaCatalogo(navController, userList)
        }
        composable("screenB") {
            PantallaRegistro(navController, userList)
        }
    }
}
