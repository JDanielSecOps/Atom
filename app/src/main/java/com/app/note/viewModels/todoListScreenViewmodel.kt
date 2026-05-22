package com.app.note.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.note.components.globalComponents.sortType
import com.app.note.repository.todoRepo
import com.app.note.screens.todoListScreen.todoListScreenIntent
import com.app.note.screens.todoListScreen.todoListScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch



class todoListScreenViewmodel(val todorepo : todoRepo) : ViewModel(){


    val _sorting = MutableStateFlow<sortType>(sortType.CREATED_ASCENDING)

    private val _state = MutableStateFlow(todoListScreenState())
    @OptIn(ExperimentalCoroutinesApi::class)
    private val todolist = _sorting.flatMapLatest { sort ->
        when(sort){
            sortType.CREATED_ASCENDING -> todorepo.created_ascending
            sortType.CREATED_DESCENDING -> todorepo.created_descending
            sortType.DEADLINE_ASCENDING -> todorepo.deadline_ascending
            sortType.DEADLINE_DESCENDING -> todorepo.deadline_descending

        }

    }


    val state  = combine(_state,todolist){state,todolist->
        state.copy(
            todolist = todolist
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), todoListScreenState(
        isLoading = true
    ))


    fun onAction(events : todoListScreenIntent){

        when(events){
            is todoListScreenIntent.deleteSingle ->{
                viewModelScope.launch {
                    todorepo.updateTempDeleteStatus(events.id,true)
                }
            }
            is todoListScreenIntent.sort ->{
                _sorting.value =events.sortType
            }
        }
    }
}