
package com.example.superheroesmvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SuperheroesViewModel : ViewModel() {

    private val _heroes =
        MutableStateFlow<List<Nombres>>(emptyList())

    val heroes: StateFlow<List<Nombres>> = _heroes

    private val _cargando = MutableStateFlow(true)
    val cargando: StateFlow<Boolean> = _cargando

    init {
        cargarSuperheroes()
    }

    fun cargarSuperheroes() {
        viewModelScope.launch {

            _cargando.value = true
            delay(1000)

            val url = "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/"

            _heroes.value = listOf(

                Nombres(
                    id = 1,
                    nombre = "Spider-Man",
                    imagenUrl = "${url}620-spider-man.jpg",
                    descripcion = "Peter Parker protege Nueva York utilizando sus poderes arácnidos.",
                    superPoderes = listOf(
                        "Trepar paredes",
                        "Sentido arácnido",
                        "Agilidad",
                        "Superfuerza"
                    )
                ),

                Nombres(
                    id = 13,
                    nombre = "Green Goblin",
                    imagenUrl = "${url}299-green-goblin.jpg",
                    descripcion = "Norman Osborn es uno de los mayores enemigos de Spider-Man. Utiliza un planeador y armas explosivas.",
                    superPoderes = listOf(
                        "Superfuerza",
                        "Inteligencia",
                        "Bombas calabaza",
                        "Tecnología avanzada"
                    )
                ),

                Nombres(
                    id = 2,
                    nombre = "Iron Man",
                    imagenUrl = "${url}346-iron-man.jpg",
                    descripcion = "Tony Stark es un inventor que utiliza una avanzada armadura tecnológica.",
                    superPoderes = listOf(
                        "Vuelo",
                        "Rayos repulsores",
                        "Inteligencia",
                        "Tecnología avanzada"
                    )
                ),

                Nombres(
                    id = 14,
                    nombre = "Ultron",
                    imagenUrl = "${url}680-ultron.jpg",
                    descripcion = "Inteligencia artificial creada para proteger la Tierra que termina enfrentándose a los Vengadores.",
                    superPoderes = listOf(
                        "Inteligencia artificial",
                        "Fuerza robótica",
                        "Control tecnológico",
                        "Rayos de energía"
                    )
                ),

                Nombres(
                    id = 3,
                    nombre = "Thor",
                    imagenUrl = "${url}659-thor.jpg",
                    descripcion = "El dios del trueno de Asgard y protector de los nueve reinos.",
                    superPoderes = listOf(
                        "Control del trueno",
                        "Superfuerza",
                        "Vuelo",
                        "Resistencia"
                    )
                ),

                Nombres(
                    id = 15,
                    nombre = "Loki",
                    imagenUrl = "${url}414-loki.jpg",
                    descripcion = "Dios del engaño y hermano adoptivo de Thor, conocido por su magia y sus ilusiones.",
                    superPoderes = listOf(
                        "Magia",
                        "Ilusiones",
                        "Cambio de apariencia",
                        "Inteligencia"
                    )
                ),

                Nombres(
                    id = 4,
                    nombre = "Superman",
                    imagenUrl = "${url}644-superman.jpg",
                    descripcion = "Héroe de Krypton que protege la Tierra con sus extraordinarios poderes.",
                    superPoderes = listOf(
                        "Vuelo",
                        "Supervelocidad",
                        "Visión de calor",
                        "Superfuerza"
                    )
                ),

                Nombres(
                    id = 16,
                    nombre = "Darkseid",
                    imagenUrl = "${url}204-darkseid.jpg",
                    descripcion = "Poderoso gobernante de Apokolips que busca dominar el universo.",
                    superPoderes = listOf(
                        "Rayos Omega",
                        "Superfuerza",
                        "Resistencia extrema",
                        "Manipulación de energía"
                    )
                ),

                Nombres(
                    id = 5,
                    nombre = "Batman",
                    imagenUrl = "${url}70-batman.jpg",
                    descripcion = "Bruce Wayne combate el crimen en Gotham utilizando inteligencia y tecnología.",
                    superPoderes = listOf(
                        "Artes marciales",
                        "Inteligencia",
                        "Estrategia",
                        "Tecnología"
                    )
                ),

                Nombres(
                    id = 17,
                    nombre = "Joker",
                    imagenUrl = "${url}370-joker.jpg",
                    descripcion = "El Joker es un criminal de Gotham City y uno de los principales enemigos de Batman.",
                    superPoderes = listOf(
                        "Inteligencia criminal",
                        "Manipulación",
                        "Estrategia",
                        "Armas químicas"
                    )
                ),

                Nombres(
                    id = 6,
                    nombre = "Wonder Woman",
                    imagenUrl = "${url}720-wonder-woman.jpg",
                    descripcion = "Diana es una guerrera amazona que lucha por la justicia.",
                    superPoderes = listOf(
                        "Superfuerza",
                        "Combate",
                        "Resistencia",
                        "Velocidad"
                    )
                ),

                Nombres(
                    id = 18,
                    nombre = "Thanos",
                    imagenUrl = "${url}655-thanos.jpg",
                    descripcion = "El Titán Loco busca imponer su visión del equilibrio universal y utiliza el Guantelete del Infinito.",
                    superPoderes = listOf(
                        "Superfuerza",
                        "Resistencia",
                        "Inteligencia",
                        "Poder de las Gemas del Infinito"
                    )
                ),

                Nombres(
                    id = 7,
                    nombre = "Hulk",
                    imagenUrl = "${url}332-hulk.jpg",
                    descripcion = "Bruce Banner se transforma en un poderoso gigante verde.",
                    superPoderes = listOf(
                        "Fuerza extrema",
                        "Resistencia",
                        "Regeneración",
                        "Grandes saltos"
                    )
                ),

                Nombres(
                    id = 19,
                    nombre = "Doctor Doom",
                    imagenUrl = "${url}222-doctor-doom.jpg",
                    descripcion = "Victor von Doom es un poderoso científico y hechicero, gobernante de Latveria.",
                    superPoderes = listOf(
                        "Magia",
                        "Inteligencia superior",
                        "Armadura tecnológica",
                        "Rayos de energía"
                    )
                ),

                Nombres(
                    id = 8,
                    nombre = "Wolverine",
                    imagenUrl = "${url}717-wolverine.jpg",
                    descripcion = "Logan es un mutante con habilidades regenerativas y garras de adamantium.",
                    superPoderes = listOf(
                        "Regeneración",
                        "Garras",
                        "Combate",
                        "Sentidos mejorados"
                    )
                ),

                Nombres(
                    id = 20,
                    nombre = "Venom",
                    imagenUrl = "${url}687-venom.jpg",
                    descripcion = "Eddie Brock se une a un simbionte alienígena y se convierte en Venom, enemigo y ocasional aliado de Spider-Man.",
                    superPoderes = listOf(
                        "Superfuerza",
                        "Regeneración",
                        "Cambio de forma",
                        "Trepar paredes"
                    )
                ),

                Nombres(
                    id = 9,
                    nombre = "Black Widow",
                    imagenUrl = "${url}107-black-widow.jpg",
                    descripcion = "Natasha Romanoff es una espía experta en combate y operaciones especiales.",
                    superPoderes = listOf(
                        "Espionaje",
                        "Combate",
                        "Agilidad",
                        "Estrategia"
                    )
                ),

                Nombres(
                    id = 10,
                    nombre = "Black Panther",
                    imagenUrl = "${url}106-black-panther.jpg",
                    descripcion = "T'Challa es el protector de Wakanda y portador del poder de la Pantera Negra.",
                    superPoderes = listOf(
                        "Agilidad",
                        "Combate",
                        "Superfuerza",
                        "Tecnología"
                    )
                ),

                Nombres(
                    id = 11,
                    nombre = "Spider-Gwen",
                    imagenUrl = "${url}619-spider-gwen.jpg",
                    descripcion = "Gwen Stacy es una superheroína con habilidades arácnidas.",
                    superPoderes = listOf(
                        "Trepar paredes",
                        "Agilidad",
                        "Sentido arácnido",
                        "Superfuerza"
                    )
                ),

                Nombres(
                    id = 12,
                    nombre = "Star-Lord",
                    imagenUrl = "${url}630-star-lord.jpg",
                    descripcion = "Peter Quill lidera a los Guardianes de la Galaxia.",
                    superPoderes = listOf(
                        "Combate",
                        "Pilotaje",
                        "Estrategia",
                        "Armas tecnológicas"
                    )
                )
            )

            _cargando.value = false
        }
    }

    fun cambiarFavorito(id: Int) {

        _heroes.value = _heroes.value.map { personaje ->

            if (personaje.id == id) {

                personaje.copy(
                    esFavorito = !personaje.esFavorito
                )

            } else {

                personaje

            }
        }
    }
}
