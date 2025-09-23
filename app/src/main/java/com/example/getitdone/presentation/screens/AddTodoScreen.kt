package com.example.getitdone.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.getitdone.data.models.ToDo
import com.example.getitdone.presentation.viewmodels.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoScreen(
    viewModel: TodoViewModel,
    navController: NavController
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val isCompleted by remember { mutableStateOf(false) }

    Column {
        CenterAlignedTopAppBar(
            title = { Text("Add Todo") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )
        TextField(
            value = title,
            onValueChange = {title = it},
            placeholder = {Text("Let's get started")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        )
        Spacer(Modifier.padding(30.dp))
        TextField(
            value = description,
            onValueChange = {description = it},
            placeholder = {Text("A bit more detail")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .size(300.dp)
        )
        FloatingActionButton(
            modifier = Modifier.padding(top = 30.dp, end = 20.dp)
                .align(alignment = Alignment.End),
            shape = CircleShape,
            containerColor = Color.Black,
            onClick = {
                if (title.isNotBlank() && description.isNotBlank()) {
                    viewModel.addTodo(title, description, isCompleted)
                    navController.popBackStack()
                }
            }
        ) {
            Icon(
                Icons.Filled.Done,
                null,
                tint = Color.White
            )
        }
    }
}