package com.app.note.functions

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


fun FormatTime(timestamp : LocalDateTime?) : String?{

    if(timestamp != null){

        val formatting = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm a", Locale.getDefault())
        return  timestamp.format(formatting)

    }


    return null
}


fun FormatDate(timestamp : LocalDateTime?) : String?{

    if(timestamp != null){

        val formatting = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault())
        return  timestamp.format(formatting)

    }


    return null
}
