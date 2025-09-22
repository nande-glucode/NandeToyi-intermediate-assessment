package com.example.getitdone.data.persistance

import com.example.getitdone.data.models.ToDo
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val dao: TodoDao) {
    val allTodos: Flow<List<ToDo>> = dao.getAllTodos()

    suspend fun addTodo(todo: ToDo) = dao.insertTodo(todo)
    suspend fun deleteTodo(todo: ToDo) = dao.deleteTodo(todo)
    suspend fun updateTodo(todo: ToDo) = dao.update(todo.id, todo.title, todo.description, todo.isCompleted)
}