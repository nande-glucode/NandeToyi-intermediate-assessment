package com.example.getitdone.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getitdone.data.models.ToDo
import com.example.getitdone.data.persistance.TodoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository): ViewModel() {

    val todos: StateFlow<List<ToDo>> = repository.allTodos.map {
        it.sortedByDescending { todo -> todo.id } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTodo(title: String, description: String, isCompleted: Boolean) {
        viewModelScope.launch {
            val todo = ToDo(
                title = title,
                description = description,
                isCompleted = isCompleted
            )
            repository.addTodo(todo)
        }
    }

    fun deleteTodo(todo: ToDo) = viewModelScope.launch {
        repository.deleteTodo(todo)
    }

    fun toggleDone(todo: ToDo) {
        viewModelScope.launch {
            val updatedTodo = todo.copy(isCompleted = !todo.isCompleted)
            repository.updateTodo(updatedTodo)
        }
    }
}