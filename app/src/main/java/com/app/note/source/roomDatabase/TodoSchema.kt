


package com.app.note.source.roomDatabase

import android.R
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.note.serializer.dateTimeSearializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID



@Serializable
@Entity
data class TodoTable(

    @PrimaryKey val id : String = UUID.randomUUID().toString(),

    @ColumnInfo("title")val title: String,
    @ColumnInfo(name = "Todo") val Todo : String,

    @Serializable(with = dateTimeSearializer::class)
    @ColumnInfo(name="created_timestamp") val createdTimestamp : LocalDateTime,

    @Serializable(with = dateTimeSearializer::class)
    @ColumnInfo(name = "deadline_timestamp") val deadlineTimeStamp : LocalDateTime?,

    @ColumnInfo(name="deleted") val deleted : Boolean

)


