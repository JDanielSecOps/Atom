package com.app.note.source.roomDatabase

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

class Converters {

    private val  zoneid = ZoneId.systemDefault()

    @TypeConverter
    fun fromLongToTimestamp(value : Long?) : LocalDateTime?{
        return  value?.let { it ->
            Instant.ofEpochMilli(it)
                .atZone((zoneid))
                .toLocalDateTime()
        }
    }

    @TypeConverter
    fun fromTimestampToLong(date : LocalDateTime?): Long?{
        return  date?.let { it ->
            it.atZone(zoneid)
                .toInstant()
                .toEpochMilli()
        }
    }
}