package edu.itvo.roompersistence.presentation.player.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import edu.itvo.roompersistence.presentation.player.component.PlayerItem
import edu.itvo.roompersistence.presentation.player.event.PlayerListEvent
import edu.itvo.roompersistence.presentation.player.viewmodel.PlayerListViewModel
import androidx.compose.foundation.layout.Column
@Composable
fun PlayerListScreen(
    onNavigateToAddPlayer: () -> Unit,
    onNavigateToEditPlayer: (Long) -> Unit,
    onNavigateToStadiums: () -> Unit, // 👈 NUEVO
    viewModel: PlayerListViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(

        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                // ➕ ADD PLAYER
                FloatingActionButton(
                    onClick = onNavigateToAddPlayer
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Agregar jugador"
                    )
                }

                // 🏟️ STADIUMS
                FloatingActionButton(
                    onClick = onNavigateToStadiums
                ) {
                    Text("🏟️")
                }
            }
        }

    ) { innerPadding ->

        if (uiState.players.isEmpty()) {

            EmptyPlayerScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = uiState.players,
                    key = { it.id }
                ) { player ->

                    PlayerItem(
                        player = player,
                        onEditClick = {
                            onNavigateToEditPlayer(player.id)
                        },
                        onDeleteClick = {
                            viewModel.onEvent(
                                PlayerListEvent.DeletePlayer(player)
                            )
                        }
                    )
                }
            }
        }
    }
}