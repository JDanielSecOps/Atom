package com.app.note.screens.backupsScreen

import android.net.Uri

sealed interface backupsScreenIntent {

    data class exportNote(val URI: Uri?) : backupsScreenIntent
    data class importNote(val URI: Uri?) : backupsScreenIntent


}