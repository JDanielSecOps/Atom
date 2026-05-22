package com.app.note.components.globalComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

enum class sortType{

    CREATED_ASCENDING,
    CREATED_DESCENDING,
    DEADLINE_ASCENDING,
    DEADLINE_DESCENDING
}

data class sortButtonContent(

    val content : String,
    val type : sortType
)

@Composable
fun Sorter(
    sortingFn : (sortType : sortType)-> Unit
){

    val content : List<sortButtonContent> =listOf(
        sortButtonContent("Created Ascending", sortType.CREATED_ASCENDING),
        sortButtonContent("Created Descending", sortType.CREATED_DESCENDING),
        sortButtonContent("Deadline Ascending", sortType.DEADLINE_ASCENDING),
        sortButtonContent("Deadline Descending", sortType.DEADLINE_DESCENDING)

    )

    Row(horizontalArrangement =Arrangement.spacedBy(8.dp)
        , verticalAlignment = Alignment.CenterVertically
        , modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(
            horizontal = 8.dp
        )
    ){
        content.forEach { it ->
            Button(onClick = { sortingFn(it.type) }, shape = RoundedCornerShape(5.dp)
            ) {
                Text(it.content)
            }
        }
    }
}