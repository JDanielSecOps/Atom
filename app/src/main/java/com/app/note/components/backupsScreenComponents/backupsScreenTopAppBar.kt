package com.app.note.components.backupsScreenComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun backupsScreentopAppBar(navToSettings : ()-> Unit){

    TopAppBar(
        title = { Text("Backup") },
        navigationIcon = {
            IconButton(
                onClick = navToSettings
            ) {
                Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
            }
        }
    )
}