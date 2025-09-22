package com.example.getitdone.data.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.getitdone.data.models.ToDo

@Database(entities = [ToDo::class], version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}