package com.example.getitdone.data.persistance

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.getitdone.data.models.ToDo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAllTodos(): Flow<List<ToDo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(toDo: ToDo)

    @Delete
    suspend fun deleteTodo(toDo: ToDo)

    @Query("UPDATE todos set title = :title, description = :description, isCompleted = :isCompleted where id = :id ")
    suspend fun update(id: Int?, title: String?, description: String?, isCompleted: Boolean)
}