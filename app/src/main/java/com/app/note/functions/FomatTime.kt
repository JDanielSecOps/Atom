package com.app.note.functions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


fun FormatTime(timestamp : LocalDateTime?) : String?{

    if(timestamp != null){

        val formatting = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm a", Locale.getDefault())
        return  timestamp.format(formatting)

    }


    return null
}