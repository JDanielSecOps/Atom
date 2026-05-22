package com.app.note.components.viewTodoListScreenComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.app.note.functions.FormatTime
import java.time.LocalDateTime


@Composable
fun todoCard(

    Id: String,
    title: String,
    createdDate: LocalDateTime,
    deadlineDate: LocalDateTime?,
    redirect: (String) -> Unit,
    update: () -> Unit,
    delete: () -> Unit
){
    Column(Modifier.fillMaxWidth().border(2.dp,
        MaterialTheme.colorScheme.primary,
        RoundedCornerShape(16.dp)
    ).clickable(
        onClick ={
            redirect(Id)
        }
    )
    ){
        Column(Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)){
            Text("Title : ${title}", overflow = TextOverflow.Ellipsis, modifier = Modifier.fillMaxWidth(),
                maxLines = 1)

            Text("Created : ${FormatTime(createdDate)} ")

            if(deadlineDate is LocalDateTime){
                Text("Deadline : ${FormatTime(deadlineDate)} ")
            }
            Row(modifier = Modifier.align(Alignment.End)){
                IconButton(
                    onClick = {update()}
                ) {
                    Icon(Icons.Filled.Edit,null)
                }
                IconButton(
                    onClick = {delete()}
                ) {
                    Icon(Icons.Filled.Delete,null)
                }
            }
        }
    }
}