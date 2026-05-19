package com.app.note.source.roomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TodoTable::class]
, version = 1)
@TypeConverters(Converters::class)
abstract class todoDatabase : RoomDatabase() {

    abstract  fun todoDao() : todoDao
}