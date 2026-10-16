package com.example.superheroesmvvm

data class Nombres(
    val id: Int,
    val nombre: String,
    val imagenUrl: String,
    val descripcion: String,
    val superPoderes: List<String>,
    val esFavorito: Boolean = false
)