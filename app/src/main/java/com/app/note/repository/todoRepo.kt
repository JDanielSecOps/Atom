package com.app.note.repository

import com.app.note.source.roomDatabase.TodoTable
import com.app.note.source.roomDatabase.todoDatabase
import kotlinx.coroutines.flow.Flow

class todoRepo(val todoDatabase: todoDatabase) {

    private val todoDatabseInstance =todoDatabase.todoDao()

    val allTodos: Flow<List<TodoTable>> =todoDatabseInstance.getAllTodo(false)

    val allData : Flow<List<TodoTable>> = todoDatabseInstance.getAllData()
    val created_ascending : Flow<List<TodoTable>> =todoDatabseInstance.todoSortedByCreatedAscending(false)
    val created_descending : Flow<List<TodoTable>> =todoDatabseInstance.todoSortedByCreatedDecending(false)
    val deadline_ascending : Flow<List<TodoTable>> =todoDatabseInstance.todoSortedByDeadlineAscending(false)
    val deadline_descending : Flow<List<TodoTable>> =todoDatabseInstance.todoSortedByDeadlineDecending(false)

    val deleated_created_ascending : Flow<List<TodoTable>> =todoDatabseInstance.todoSortedByCreatedAscending(true)
    val deleated_created_descending : Flow<List<TodoTable>> =todoDatabseInstance.todoSortedByCreatedDecending(true)
    val deleated_deadline_ascending : Flow<List<TodoTable>> =todoDatabseInstance.todoSortedByDeadlineAscending(true)
    val deleated_deadline_descending : Flow<List<TodoTable>> =todoDatabseInstance.todoSortedByDeadlineDecending(true)


    val getdeletionListOFTodos : Flow<List<TodoTable>> =todoDatabseInstance.getAllDeleted(true)
    fun getTodoesById(id: String) : Flow<List<TodoTable>> =todoDatabseInstance.getTodobyId(id)

    suspend fun deleteTodo(todo : TodoTable){
        todoDatabseInstance.deleteTodo(todo)
    }

    suspend fun updateTodo(todo: TodoTable){
        todoDatabseInstance.updateTodo(todo)
    }

    suspend fun insertTodo(todo: TodoTable){
        todoDatabseInstance.insertTodo(todo)
    }

    suspend fun insertAllTodo(todo : List<TodoTable>){
        todoDatabseInstance.insertAllTodo(todo)
    }

    suspend fun updateTempDeleteStatus(id: String, status: Boolean){
        todoDatabseInstance.updateTempDeleteStatus(id,status)
    }

    suspend fun deleteListOfTodo(todoList : List<TodoTable>){
        todoDatabseInstance.deleteListOfTodo(todoList)
    }

}