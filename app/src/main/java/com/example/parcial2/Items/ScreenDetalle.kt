package com.example.parcial2.Items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.parcial2.R
@Composable
fun PantallaDetalle(
    navController: NavController,
    producto: Producto?,
    carrito: SnapshotStateList<Producto>
) {
    if (producto == null) {
        Text("Producto no encontrado", style = MaterialTheme.typography.bodyLarge)
        return
    }

    val painter = rememberAsyncImagePainter(
        model = producto.imagenUrl,
        placeholder = painterResource(id = R.drawable.placeholder_image),
        error = painterResource(id = R.drawable.error_image)
    )

    val imageState = painter.state

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
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Detalle del Producto",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                when (imageState) {
                    is AsyncImagePainter.State.Error,
                    is AsyncImagePainter.State.Empty -> {
                        Image(
                            painter = painterResource(id = R.drawable.error_image),
                            contentDescription = "Imagen no disponible",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(180.dp)
                        )
                    }
                    else -> {
                        AsyncImage(
                            model = producto.imagenUrl,
                            contentDescription = "Imagen del producto",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(180.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Nombre: ${producto.nombre}", style = MaterialTheme.typography.titleMedium)
            Text("Precio: $${producto.precio}", style = MaterialTheme.typography.titleMedium)
            Text("Descripción: ${producto.descripcion}", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    carrito.add(producto)
                    navController.navigate("screenA")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar al Carrito")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { navController.navigate("screenA") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Volver al Catálogo")
            }
        }
    }
}
