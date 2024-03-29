package com.example.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.local.EntityMovie


@Database(entities = [EntityMovie::class], version = 1, exportSchema = false)
abstract class DBMovie : RoomDatabase() {
    abstract fun dao(): DaoMovie
}