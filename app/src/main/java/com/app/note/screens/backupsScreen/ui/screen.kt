package com.app.note.screens.backupsScreen.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.app.note.components.backupsScreenComponents.backupsScreentopAppBar
import com.app.note.components.globalComponents.generalScreenScaffoldPadding
import com.app.note.functions.FormatTime
import com.app.note.screens.backupsScreen.backupsScreenIntent
import com.app.note.viewModels.backupsScreenViewmodel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime


@Composable
fun backupsScreenRoot(navToSetting : ()-> Unit,
                      viewmodel: backupsScreenViewmodel = koinViewModel<backupsScreenViewmodel>()
){


    val  LayoutDirection = LocalLayoutDirection.current

    Scaffold(
        topBar = {
           backupsScreentopAppBar(navToSetting)
        }
    ){ paddingValues ->
        backupsScreen(paddingValues,LayoutDirection,viewmodel::onAction)
    }
}


@Composable
fun backupsScreen(paddingValues: PaddingValues,LayoutDirection : LayoutDirection,
                  onAction : (backupsScreenIntent)-> Unit

){

    val exportContent= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/json"),
        onResult = {Uri ->
                onAction(backupsScreenIntent.exportNote(Uri))
        }
    )


    val importContent=rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent() ,
        onResult = {Uri->
                onAction(backupsScreenIntent.importNote(Uri))
        }
    )

    Column (modifier = generalScreenScaffoldPadding(paddingValues,LayoutDirection),

    ){

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)
            , modifier = Modifier.padding(16.dp)){

            Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier
                .border(2.dp, MaterialTheme.colorScheme.secondary
                    , RoundedCornerShape(16.dp))){
                Text("To retrieve your data from the backups utilize the import feature",
                    modifier = Modifier.padding(16.dp))
                Button(onClick = {
                    importContent.launch("application/json")
                }, shape = RoundedCornerShape(5.dp)
                    , modifier = Modifier.align(Alignment.End).padding(16.dp)) {
                    Text("Import")
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.border(2.dp, MaterialTheme.colorScheme.secondary
                    , RoundedCornerShape(16.dp))){
                Text("To store data as a backup utilize the export feature",
                    modifier = Modifier.padding(16.dp))
                Button(onClick = {
                    exportContent.launch("Note_Backup_${FormatTime(LocalDateTime.now())}")
                }, shape = RoundedCornerShape(5.dp)
                    , modifier = Modifier.align(Alignment.End).padding(16.dp)) {
                    Text("Export")
                }
            }

        }
    }

}