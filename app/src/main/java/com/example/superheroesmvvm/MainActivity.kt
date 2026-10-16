
package com.example.superheroesmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[
            SuperheroesViewModel::class.java
        ]

        setContent {
            MaterialTheme {
                SuperheroesApp(viewModel)
            }
        }
    }
}

// COMPONENTE REUTILIZABLE PARA LAS IMAGENES

@Composable
fun HeroImage(
    imagenUrl: String,
    nombre: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = imagenUrl,
        contentDescription = "Imagen de $nombre",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

// NAVEGACION DE LA APLICACION

@Composable
fun SuperheroesApp(viewModel: SuperheroesViewModel) {

    val heroes by viewModel.heroes.collectAsState()
    val cargando by viewModel.cargando.collectAsState()

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "lista"
    ) {

        composable("lista") {

            PantallaLista(
                heroes = heroes,
                cargando = cargando,
                onFavorito = { id ->
                    viewModel.cambiarFavorito(id)
                },
                onDetalle = { id ->
                    navController.navigate("detalle/$id")
                }
            )
        }

        composable(
            route = "detalle/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { entrada ->

            val id = entrada.arguments?.getInt("id")

            val heroe = heroes.find {
                it.id == id
            }

            if (heroe != null) {
                PantallaDetalle(
                    heroe = heroe,
                    onVolver = {
                        navController.popBackStack()
                    },
                    onFavorito = {
                        viewModel.cambiarFavorito(heroe.id)
                    }
                )
            }
        }
    }
}

// PANTALLA PRINCIPAL

@Composable
fun PantallaLista(
    heroes: List<Nombres>,
    cargando: Boolean,
    onFavorito: (Int) -> Unit,
    onDetalle: (Int) -> Unit
) {

    Scaffold(
        topBar = {
            Text(
                text = "Mis Superhéroes",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp)
            )
        }
    ) { padding ->

        if (cargando) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    CircularProgressIndicator()

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Text("Cargando superhéroes...")
                }
            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(
                    items = heroes,
                    key = { it.id }
                ) { heroe ->

                    HeroCard(
                        heroe = heroe,
                        onFavorito = {
                            onFavorito(heroe.id)
                        },
                        onDetalle = {
                            onDetalle(heroe.id)
                        }
                    )
                }
            }
        }
    }
}

// TARJETA REUTILIZABLE

@Composable
fun HeroCard(
    heroe: Nombres,
    onFavorito: () -> Unit,
    onDetalle: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onDetalle()
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column {

            HeroImage(
                imagenUrl = heroe.imagenUrl,
                nombre = heroe.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = heroe.nombre,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text = heroe.descripcion,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    IconButton(
                        onClick = onFavorito
                    ) {

                        Text(
                            text = if (heroe.esFavorito) "♥" else "♡",
                            style = MaterialTheme.typography.headlineLarge,
                            color = if (heroe.esFavorito) {
                                MaterialTheme.colorScheme.error
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            }
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Ver detalles →",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

// PANTALLA DE DETALLES

@Composable
fun PantallaDetalle(
    heroe: Nombres,
    onVolver: () -> Unit,
    onFavorito: () -> Unit
) {

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {

            // IMAGEN GRANDE DEL HEROE

            HeroImage(
                imagenUrl = heroe.imagenUrl,
                nombre = heroe.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            )

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Text(
                    text = heroe.nombre,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = "Descripción",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = heroe.descripcion
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = "Superpoderes",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                heroe.superPoderes.forEach { poder ->

                    AssistChip(
                        onClick = {},
                        label = {
                            Text(poder)
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )
                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                // AGREGAR O QUITAR FAVORITO

                Button(
                    onClick = onFavorito,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        if (heroe.esFavorito) {
                            "♥ Quitar de favoritos"
                        } else {
                            "♡ Agregar a favoritos"
                        }
                    )
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                // VOLVER A LA LISTA

                OutlinedButton(
                    onClick = onVolver,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("← Volver a la lista")
                }
            }
        }
    }
}
