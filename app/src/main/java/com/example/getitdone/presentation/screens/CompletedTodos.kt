package com.example.getitdone.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.getitdone.presentation.components.TodoItem
import com.example.getitdone.presentation.viewmodels.TodoViewModel

@Composable
fun CompletedTodos(viewModel: TodoViewModel) {
    val todos by viewModel.todos.collectAsState()

    Column(Modifier.padding(16.dp)) {
        Spacer(Modifier.padding(30.dp))
        LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
            items(todos) { todo ->
                TodoItem(
                    todo = todo,
                    onDelete = {viewModel.deleteTodo(todo)},
                    onToggle = {
                        viewModel.toggleDone(todo)
                    }
                )
            }
        }
    }
}