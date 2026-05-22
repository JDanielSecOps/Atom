package com.app.note.repository

import android.content.Context
import android.net.Uri
import com.app.note.source.roomDatabase.TodoTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json



class fileOperations(
    private  val  context : Context,
    private val todoRepo: todoRepo
) {

    suspend fun writeToFile(URI : Uri?)= withContext(Dispatchers.IO){

        try {
            if(URI != null){

                val prettyjson=Json { prettyPrint = true }
                val data = todoRepo.allData.firstOrNull() ?: emptyList()
                val json = prettyjson.encodeToString(data)
                val file = context.contentResolver.openOutputStream(URI).use { ouputstream->

                    ouputstream?.write(json.toByteArray(Charsets.UTF_8))
                    ouputstream?.flush()
                }

            }

        }catch (e : Exception){
            e.printStackTrace()
        }
    }
    suspend fun readFromFile(URI: Uri?) =withContext(Dispatchers.IO){

        try {

            if(URI != null){

                context.contentResolver.openInputStream(URI).use {inputStream ->

                if(inputStream != null){

                    val data =inputStream.bufferedReader(Charsets.UTF_8).use {
                        it.readText()
                    }
                    val decode = Json.decodeFromString<List<TodoTable>>(data)
                    todoRepo.insertAllTodo(decode)

                }

                }
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }


}