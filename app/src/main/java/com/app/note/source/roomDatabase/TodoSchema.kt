package com.app.note.source.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity
data class TodoTable(

    @PrimaryKey(autoGenerate = true) val id : Int=0,

    @ColumnInfo("title")val title: String,
    @ColumnInfo(name = "Todo") val Todo : String,

    @ColumnInfo(name="created_timestamp") val createdTimestamp : LocalDateTime,

    @ColumnInfo(name = "deadline_timestamp") val deadlineTimeStamp : LocalDateTime?,

    @ColumnInfo(name="deleted") val deleted : Boolean

)
