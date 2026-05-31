package edu.itvo.roompersistence.presentation.player.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import edu.itvo.roompersistence.domain.model.Stadium

@Composable
fun StadiumItem(
    stadium: Stadium,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text("🏟️ ${stadium.name}")
            Text("📍 ${stadium.city}, ${stadium.country}")
            Text("👥 ${stadium.capacity}")

            Button(onClick = onDelete) {
                Text("Eliminar")
            }
        }
    }
}