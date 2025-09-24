package com.example.getitdone.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.getitdone.data.models.ToDo

@Composable
fun TodoItem(
    todo: ToDo,
    onDelete: (ToDo) -> Unit,
    onToggle: (ToDo) -> Unit,
) {
    Card(
        Modifier.fillMaxWidth().padding(vertical = 4.dp)
            .size(150.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Row(
                Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = todo.isCompleted,
                        onCheckedChange = { onToggle(todo) }
                    )
                    Text(
                        text = todo.title,
                        modifier = Modifier.weight(1f),
                        style = if (todo.isCompleted)
                            MaterialTheme.typography.bodyLarge.copy(textDecoration = TextDecoration.LineThrough)
                        else
                            MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                    IconButton(
                        onClick = { onDelete(todo) }
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
            Text(
                text = todo.description,
                modifier = Modifier.weight(1f)
                    .padding(start = 25.dp),
                color = Color.Black
            )

        }
    }
}