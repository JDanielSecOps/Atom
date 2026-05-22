package com.app.note.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.note.repository.fileOperations
import com.app.note.screens.backupsScreen.backupsScreenIntent
import kotlinx.coroutines.launch

class backupsScreenViewmodel(
    private val fileOperations: fileOperations
): ViewModel() {




    fun onAction(events : backupsScreenIntent){

        when(events){
            is backupsScreenIntent.exportNote ->{

                viewModelScope.launch {
                    fileOperations.writeToFile(events.URI)
                }
            }
            is backupsScreenIntent.importNote ->{

                viewModelScope.launch {
                    fileOperations.readFromFile(events.URI)
                }
            }
        }
    }
}