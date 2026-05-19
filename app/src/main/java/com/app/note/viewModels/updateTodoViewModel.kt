package com.app.note.viewModels

import android.util.Log
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.note.components.globalComponents.millistoDate
import com.app.note.components.globalComponents.numtoTime
import com.app.note.functions.FormatDate
import com.app.note.functions.FormatTime
import com.app.note.repository.todoRepo
import com.app.note.screens.addTodoScreen.addTodoScreenState
import com.app.note.screens.updateTodoScreen.ui.channel.updateTodoScreenChannel
import com.app.note.screens.updateTodoScreen.ui.updateTodoScreenIntent
import com.app.note.screens.updateTodoScreen.ui.updateTodoScreenState
import com.app.note.source.roomDatabase.TodoTable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset



class updateTodoViewModel(
    val Id : Int,
    val todoRepo: todoRepo): ViewModel() {

    val todofield_state = TextFieldState()
    private val _state
    = MutableStateFlow(updateTodoScreenState())

    val state =_state.asStateFlow()


    private val _channel_events = Channel<updateTodoScreenChannel>()
    val channel_events =_channel_events.receiveAsFlow()


    init {

        viewModelScope.launch {
            todoRepo.getTodoesById(Id)
                .take(1)
                .collect {todos ->

                    val todo = todos.firstOrNull() ?: return@collect
                _state.update { it->

                    val createdTimestamp = todo.createdTimestamp
                    val deletedTimestamp =todo.deadlineTimeStamp

                    it.copy(

                        title = todo.title,

                        createdMinute = createdTimestamp.minute,
                        createdHour = createdTimestamp.hour,
                        createdDateLong = createdTimestamp.toLocalDate().atStartOfDay(ZoneId.systemDefault())
                            .toInstant().toEpochMilli(),

                        deadlineMinute = deletedTimestamp?.minute,
                        deadlineHour = deletedTimestamp?.hour,
                        deadlineDateLong = createdTimestamp.toLocalDate().atStartOfDay(ZoneId.systemDefault())
                            .toInstant().toEpochMilli(),

                        deadlineDateString = FormatDate(deletedTimestamp),
                        createdDateString = FormatDate(createdTimestamp),

                        createdTimeString = numtoTime(createdTimestamp.hour,
                            createdTimestamp.minute),

                        deadlineTimeString = numtoTime(deletedTimestamp?.hour,
                        deletedTimestamp?.minute),
                    )

                }

                todofield_state.setTextAndPlaceCursorAtEnd(todo.Todo)
            }
        }
    }




    fun onAction(events : updateTodoScreenIntent){
        when(events){
            updateTodoScreenIntent.Update->{

                val id                  =Id
                val title               = state.value.title
                val todo                = todofield_state.text.toString()
                val createdDate         = Instant.ofEpochMilli(state.value.createdDateLong!!)
                    .atZone(ZoneId.systemDefault()).toLocalDateTime()
                val deadlineDate        = state.value.deadlineDateLong?.let { Instant.ofEpochMilli(it)
                    .atZone(ZoneId.systemDefault()).toLocalDateTime()}
                val createdTimeHour     = state.value.createdHour
                val createdTimeMinute   = state.value.createdMinute
                val deadlineTimeHour    = state.value.deadlineHour
                val deadlineTimeMinute  = state.value.deadlineMinute


                if(todo.isBlank()){
                    _state.update { it->
                        it.copy(
                            showTodoFieldError = true,
                            todoFieldError = "Note cannot be empty"
                        )
                    }
                    return
                }

                viewModelScope.launch{
                    eventsOnChannel(updateTodoScreenChannel.navigateToSeeTodo)
                }

                val todoentry : TodoTable = TodoTable(
                    id = id,
                    title = title,
                    Todo = todo,
                    createdTimestamp = createdDate.withHour(createdTimeHour).withMinute(createdTimeMinute),
                    deadlineTimeStamp = if(deadlineTimeMinute==null || deadlineTimeHour ==null){
                        null
                    }else{deadlineDate?.withHour(deadlineTimeHour)?.withMinute(deadlineTimeMinute)},
                    deleted = false,
                )


                Log.v("test",todoentry.toString())

                viewModelScope.launch {
                    todoRepo.updateTodo(todoentry)
                }

                Log.v("test","UPload done")
            }
            is updateTodoScreenIntent.setCreatedDate ->{

                _state.update { it->
                    it.copy(
                        createdDateLong = events.createdDate,
                        createdDateString = millistoDate(events.createdDate)
                    )
                }
            }
            is updateTodoScreenIntent.setDeadlineDate ->{

                _state.update { it->
                    it.copy(
                        deadlineDateLong = events.deadlineDate ,
                        deadlineDateString = millistoDate(events.deadlineDate )
                    )
                }

            }
            is updateTodoScreenIntent.setTitle ->{

                _state.update { it->
                    it.copy(
                        title = events.title
                    )
                }
            }
            is updateTodoScreenIntent.showCreatedDateDialog ->{

                _state.update { it->
                    it.copy(
                        showCreatedDateModal = events.showCreatedDateDialog
                    )
                }
            }
            is updateTodoScreenIntent.showDeadlineDateDialog ->{

                _state.update { it->
                    it.copy(
                        showDeadlineDateModal = events.showCreatedDateDialog
                    )
                }

            }

            is updateTodoScreenIntent.showCreatedTimeDialog ->{

                _state.update { it ->
                    it.copy(
                        showCreatedTimeModal = events.showCreatedDateDialog
                    )
                }
            }
            is updateTodoScreenIntent.showDeadlineTimeDialog ->{

                _state.update { it->
                    it.copy(
                        showDeadlineTimeModal =events.showCreatedDateDialog
                    )
                }
            }

            is updateTodoScreenIntent.setCreatedTime ->{

                val hour =events.hour
                val minutes =events.minute

                _state.update { it->
                    it.copy(
                        createdHour =hour,
                        createdMinute = minutes,
                        createdTimeString = numtoTime(hour,minutes),
                        showCreatedTimeModal = false
                    )
                }
            }

            is updateTodoScreenIntent.setDeadlineTime ->{

                val hour =events.hour
                val minutes =events.minute

                _state.update { it->
                    it.copy(
                        deadlineHour =hour,
                        deadlineMinute = minutes,
                        deadlineTimeString = numtoTime(hour,minutes),
                        showDeadlineTimeModal = false
                    )
                }
            }

            is updateTodoScreenIntent.setTitleError ->{

                _state.update {it ->

                    it.copy(
                        showTitleFieldError = events.showTitleError,
                        titleFieldError = events.titleError
                    )
                }
            }
            is updateTodoScreenIntent.setTodoError ->{

                _state.update {it ->

                    it.copy(
                        showTodoFieldError = events.showTodoError,
                        todoFieldError = events.todoError
                    )
                }


            }

        }
    }


    suspend fun eventsOnChannel(event: updateTodoScreenChannel){
        _channel_events.send(event)
    }


}