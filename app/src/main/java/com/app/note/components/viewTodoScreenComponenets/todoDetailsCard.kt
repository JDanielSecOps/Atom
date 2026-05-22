

package com.app.note.components.viewTodoListScreenComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.note.functions.FormatTime
import java.time.LocalDateTime


@Composable
fun todoDetailsCard(

    Id: String,
    title: String,
    createdDate: LocalDateTime,
    deadlineDate: LocalDateTime?,
    note: String

){

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ){
        item{
            Text("Note Details", modifier = Modifier.fillMaxWidth()
                , fontSize = 20.sp, fontWeight = FontWeight.W600,)
        }
    item(){
        Column(Modifier.border(
            width =2.dp,
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.primary,
        )){

            Column(modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)){
                Text("Title : ${title}", modifier = Modifier.fillMaxWidth()
                )

                Text("Created : ${FormatTime(createdDate)} ")

                if(deadlineDate is LocalDateTime){
                    Text("Deadline : ${FormatTime(deadlineDate)} ")
                }
            }
        }
    }
        item{
            Text("Note", modifier = Modifier.fillMaxWidth()
                , fontSize = 20.sp, fontWeight = FontWeight.W600,)
        }

        item {
            Column(modifier = Modifier.fillMaxWidth().heightIn(min = 300.dp).border(2.dp,
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(16.dp)
            )){

                Text(note,
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                )

            }
        }
    }

}