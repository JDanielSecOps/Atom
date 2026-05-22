package com.app.note.source.roomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface todoDao{

    @Query("SELECT * FROM todotable WHERE deleted = :deleted")
    fun getAllTodo(deleted : Boolean) : Flow<List<TodoTable>>

    @Query("SELECT * FROM todotable WHERE deleted = :deleted")
    fun getAllDeleted(deleted : Boolean) : Flow<List<TodoTable>>

    @Query("SELECT * FROM todotable WHERE id = :id")
    fun getTodobyId(id: String) : Flow<List<TodoTable>>

    @Query("UPDATE todotable SET deleted = :status WHERE id = :id")
    suspend fun updateTempDeleteStatus(id: String, status: Boolean)

    @Query("SELECT * FROM todotable WHERE deleted = :deleted ORDER BY created_timestamp ASC")
     fun  todoSortedByCreatedAscending(deleted: Boolean) : Flow<List<TodoTable>>

    @Query("SELECT * FROM todotable WHERE deleted = :deleted ORDER BY created_timestamp DESC")
     fun  todoSortedByCreatedDecending(deleted: Boolean) : Flow<List<TodoTable>>


    @Query("SELECT * FROM todotable WHERE deleted = :deleted ORDER BY deadline_timestamp ASC")
     fun  todoSortedByDeadlineAscending(deleted: Boolean) : Flow<List<TodoTable>>

    @Query("SELECT * FROM todotable WHERE deleted = :deleted ORDER BY deadline_timestamp DESC")
     fun  todoSortedByDeadlineDecending(deleted: Boolean) : Flow<List<TodoTable>>

     @Query("SELECT * FROM todotable")
     fun getAllData () : Flow<List<TodoTable>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllTodo(Todo : List<TodoTable>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(Todo : TodoTable)




    @Update
    suspend fun updateTodo(Todo: TodoTable)

    @Delete
    suspend fun deleteTodo(Todo: TodoTable)

    @Delete
    suspend fun deleteListOfTodo(todoList : List<TodoTable>)
}