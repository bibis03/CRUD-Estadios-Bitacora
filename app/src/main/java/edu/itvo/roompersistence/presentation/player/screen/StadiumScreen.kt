package edu.itvo.roompersistence.presentation.player.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.itvo.roompersistence.domain.model.Stadium
import edu.itvo.roompersistence.presentation.player.components.StadiumItem
import edu.itvo.roompersistence.presentation.stadium.StadiumViewModel

@Composable
fun StadiumScreen(
    viewModel: StadiumViewModel = hiltViewModel()
) {
    val stadiums by viewModel.stadiums.collectAsStateWithLifecycle()

    var name by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var capacity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "🏟️ Estadios",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") }
        )

        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Ciudad") }
        )

        OutlinedTextField(
            value = country,
            onValueChange = { country = it },
            label = { Text("País") }
        )

        OutlinedTextField(
            value = capacity,
            onValueChange = { capacity = it },
            label = { Text("Capacidad") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (name.isNotBlank()) {

                    viewModel.addStadium(
                        Stadium(
                            name = name,
                            city = city,
                            country = country,
                            capacity = capacity.toIntOrNull() ?: 0
                        )
                    )

                    name = ""
                    city = ""
                    country = ""
                    capacity = ""
                }
            }
        ) {
            Text("Agregar estadio")
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(stadiums) { stadium ->

                StadiumItem(
                    stadium = stadium,
                    onDelete = {
                        viewModel.deleteStadium(stadium)
                    }
                )
            }
        }
    }
}