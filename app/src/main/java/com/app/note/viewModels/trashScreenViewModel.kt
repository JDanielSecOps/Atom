package com.app.note.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.note.components.globalComponents.sortType
import com.app.note.repository.todoRepo
import com.app.note.screens.trashScreen.trashScreenIntent
import com.app.note.screens.trashScreen.trashScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class trashScreenViewModel(
    val todoRepo: todoRepo
): ViewModel() {


    val _state = MutableStateFlow(trashScreenState())

    val _sorting = MutableStateFlow<sortType>(sortType.CREATED_ASCENDING)


    @OptIn(ExperimentalCoroutinesApi::class)
    private val deletedTodoList = _sorting.flatMapLatest { sort ->
        when(sort){
            sortType.CREATED_ASCENDING -> todoRepo.deleated_created_ascending
            sortType.CREATED_DESCENDING -> todoRepo.deleated_created_descending
            sortType.DEADLINE_ASCENDING -> todoRepo.deleated_deadline_ascending
            sortType.DEADLINE_DESCENDING -> todoRepo.deleated_deadline_descending

        }

    }

    val state = combine(_state,deletedTodoList){states,deletedTodoLists->
        states.copy(
            deleteList = deletedTodoLists
        )
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),
        trashScreenState()
    )


    fun onAction(event: trashScreenIntent){
        when(event){
            trashScreenIntent.delete ->{

                val list = state.value.deleteList

                viewModelScope.launch {
                    todoRepo.deleteListOfTodo(list)
                }
            }

            is trashScreenIntent.deleteSingle -> {

                viewModelScope.launch {
                    todoRepo.deleteTodo(event.todo)

                }
            }
            is trashScreenIntent.restore ->{

                viewModelScope.launch {
                    todoRepo.updateTempDeleteStatus(event.Id,event.status)
                }
            }

            is trashScreenIntent.sorting ->{
                _sorting.value=event.sort
            }
        }
    }


}