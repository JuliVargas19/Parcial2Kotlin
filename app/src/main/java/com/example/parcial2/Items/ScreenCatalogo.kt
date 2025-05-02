package com.example.parcial2.Items


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.parcial2.R
import com.example.parcial2.Items.Producto
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun PantallaCatalogo(
    navController: NavController,
    userList: SnapshotStateList<Producto>,
    carro: SnapshotStateList<Producto>
) {
    val cardColors = listOf(
        Color(0xFFF7B1CE),
        Color(0xFFA5D6A7),
        Color(0xFFFFF59D),
        Color(0xFF81D4FA),
        Color(0xFFD1C4E9),
        Color(0xFFFFCCBC)
    )

    val total = carro.sumOf { it.precio }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFE0E0),
                        Color(0xFFFFF3E0),
                        Color(0xFFFFFDE7)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(userList) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = cardColors.random())
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            val painter = rememberAsyncImagePainter(
                                model = producto.imagenUrl,
                                placeholder = painterResource(id = R.drawable.placeholder_image),
                                error = painterResource(id = R.drawable.error_image)
                            )

                            Image(
                                painter = painter,
                                contentDescription = "Imagen del Producto",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text("Nombre: ${producto.nombre}", style = MaterialTheme.typography.titleMedium)
                            Text("Precio: $${producto.precio}", style = MaterialTheme.typography.bodyLarge)

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = {
                                    navController.navigate("screenC/${producto.id}")
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Ver Detalles")
                            }
                        }
                    }
                }
            }

            Column {
                Text(
                    text = "Total del carrito: $${"%.2f".format(total)}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                )

                Button(
                    onClick = { navController.navigate("screenB") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Agregar Nuevo Producto")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { navController.navigate("screenD") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver Carrito")
                }
            }
        }
    }
}
