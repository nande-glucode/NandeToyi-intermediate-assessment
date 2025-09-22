package com.example.getitdone.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class ToDo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var description: String,
    val isCompleted: Boolean = false,
   // val priority: PriorityLevel
)

//enum class PriorityLevel {
//    LOW,
//    MEDIUM,
//    HIGH
//}
