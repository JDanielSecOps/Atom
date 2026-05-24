
<p align="center">
<img width="192" height="192" alt="image" src="https://github.com/user-attachments/assets/b4230ff3-8077-4e68-847c-544f50403922" />
</p>


# Atom

## Table of contents 
    
<a href="#details">Details</a>\
<a href="#tech-stack">Tech Stack</a>\
<a href="#ui">UI</a>\
<a href="#installation">Installation</a>\
<a href="#contributions">Contribution</a>


## Details

A Simple note taking app built with kotlin and jetpack
compose

## Tech Stack
 
Database : Room\
UI : Jetpack Compose 

## Content


```text


.
├── build.gradle.kts
├── local.properties
├── proguard-rules.pro
└── src
    ├── androidTest
    │   └── java
    │       └── com
    │           └── app
    │               └── note
    │                   └── ExampleInstrumentedTest.kt
    ├── main
    │   ├── AndroidManifest.xml
    │   ├── ic_launcher-playstore.png
    │   └── java
    │       └── com
    │           └── app
    │               └── note
    │                   ├── App.kt
    │                   ├── components                      -> Consists of ui components
    │                   │   ├── addTodoScreenComponenets
    │                   │   │   ├── addNoteTAB.kt
    │                   │   │   └── addTodoDetailsTAB.kt
    │                   │   ├── backupsScreenComponents
    │                   │   │   └── backupsScreenTopAppBar.kt
    │                   │   ├── globalComponents
    │                   │   │   ├── datePickerDialog.kt
    │                   │   │   ├── datePickerTextBox.kt
    │                   │   │   ├── Navbar.kt
    │                   │   │   ├── observeAsEvent.kt
    │                   │   │   ├── ScaffoldPadding.kt
    │                   │   │   ├── sorter.kt
    │                   │   │   ├── timePickerDialog.kt
    │                   │   │   └── timePickerTextBox.kt
    │                   │   ├── settingScreenComponents
    │                   │   │   ├── settingsCard.kt
    │                   │   │   └── settingsScreenTopAppBar.kt
    │                   │   ├── themeScreenComponents
    │                   │   │   ├── themesScreenCard.kt
    │                   │   │   └── themesScreenTopAppBar.kt
    │                   │   ├── todoListScreenComponents
    │                   │   │   ├── addTodoFAB.kt
    │                   │   │   ├── todoListScreenCard.kt
    │                   │   │   └── todolistScreenTopAppBar.kt
    │                   │   ├── trashScreenComponenets
    │                   │   │   ├── trashCard.kt
    │                   │   │   └── trashScreenTopAppBar.kt
    │                   │   ├── updateTodoScreenComponents
    │                   │   │   └── updateNoteScreenTAB.kt
    │                   │   ├── viewTodoListScreenComponents
    │                   │   │   ├── todoCard.kt
    │                   │   │   └── viewTodoListScreenTAB.kt
    │                   │   ├── viewTodoScreenComponenets
    │                   │   │   └── todoDetailsCard.kt
    │                   │   └── viewTrashDataScreenComponents
    │                   │       └── viewtrashDataSceenTAB.kt
    │                   ├── dependencyInjection                 -> Consists of objects or viewmodels injected during runtime
    │                   │   └── modules.kt
    │                   ├── functions                           -> Consists of  time convertion functions
    │                   │   ├── dateAndTimeConversions.kt
    │                   │   └── FomatTime.kt
    │                   ├── intents                             
    │                   ├── MainActivity.kt                     
    │                   ├── MyApplication.kt
    │                   ├── navigation                          -> Consists of the app navigation code
    │                   │   ├── addTodo.kt
    │                   │   ├── mainNav.kt
    │                   │   ├── Routes.kt
    │                   │   ├── seeTodoNav.kt
    │                   │   ├── settingsNav.kt
    │                   │   ├── todoNav.kt
    │                   │   ├── trashNav.kt
    │                   │   └── updateTodoNav.kt
    │                   ├── repository                          -> Consits of file operations and database related
    │                   │   ├── fileOperations.kt                   operations
    │                   │   ├── settingsDataStore.kt
    │                   │   └── todoRepo.kt
    │                   ├── screens                             -> Consits of the ui implementation of the 
    │                   │   ├── addTodoScreen                       screens
    │                   │   │   ├── addTodoScreenIntent.kt
    │                   │   │   ├── addTodoScreenState.kt
    │                   │   │   ├── channel
    │                   │   │   │   └── addTodoScreenChannel.kt
    │                   │   │   └── ui
    │                   │   │       ├── addnote.kt
    │                   │   │       └── addtododetails.kt
    │                   │   ├── backupsScreen
    │                   │   │   ├── ScreenIntent.kt
    │                   │   │   ├── ScreenState.kt
    │                   │   │   └── ui
    │                   │   │       └── screen.kt
    │                   │   ├── settingsScreen
    │                   │   │   ├── settingsScreenIntent.kt
    │                   │   │   ├── settingsScreenState.kt
    │                   │   │   └── ui
    │                   │   │       └── settingsScreen.kt
    │                   │   ├── themesScreen
    │                   │   │   ├── themesScreenIntent.kt
    │                   │   │   ├── themesScreenState.kt
    │                   │   │   └── ui
    │                   │   │       └── themesScreen.kt
    │                   │   ├── todoListScreen
    │                   │   │   ├── todoListScreenIntent.kt
    │                   │   │   ├── todoListScreenState.kt
    │                   │   │   └── ui
    │                   │   │       └── todoListScreen.kt
    │                   │   ├── trashScreen
    │                   │   │   ├── trashScreenIntent.kt
    │                   │   │   ├── trashScreenState.kt
    │                   │   │   └── ui
    │                   │   │       └── trashTodoScreen.kt
    │                   │   ├── updateTodoScreen
    │                   │   │   └── ui
    │                   │   │       ├── channel
    │                   │   │       │   └── updateTodoScreenChannel.kt
    │                   │   │       ├── ui
    │                   │   │       │   ├── updateNote.kt
    │                   │   │       │   └── updateTodoDetails.kt
    │                   │   │       ├── updateTodoScreenIntent.kt
    │                   │   │       └── updateTodoScreenState.kt
    │                   │   ├── viewTodoScreen
    │                   │   │   ├── ui
    │                   │   │   │   └── viewTodoScreen.kt
    │                   │   │   ├── viewTodoScreenIntent.kt
    │                   │   │   └── viewTodoScreenState.kt
    │                   │   └── viewTrashDataScreen
    │                   │       ├── ui
    │                   │       │   └── viewTrashDataScreen.kt
    │                   │       ├── viewTrashDataScreenIntent.kt
    │                   │       └── viewTrashDataState.kt
    │                   ├── serializer                             ->   Consists of the class responsible for serialzing 
    │                   │   └── dateTimeSearializer.kt                  data when parsing them
    │                   ├── source                                 ->   consists of the database implementations
    │                   │   ├── preferencesDatastore
    │                   │   │   └── preferencesDatastore.kt
    │                   │   └── roomDatabase
    │                   │       ├── Converters.kt
    │                   │       ├── partialUpdateContracts.kt
    │                   │       ├── todoDao.kt
    │                   │       ├── todoDatabase.kt
    │                   │       └── TodoSchema.kt
    │                   ├── templateForScreens                    -> consits of the template for the screen
    │                   │   ├── ScreenIntent.kt                      implementation (mvi architecture)
    │                   │   ├── ScreenState.kt
    │                   │   └── ui
    │                   │       └── screen.kt
    │                   ├── ui                                    -> consits of the theme components  
    │                   │   └── theme                                (specifications for color,font,etc)
    │                   │       ├── Color.kt
    │                   │       ├── Theme.kt
    │                   │       └── Type.kt
    │                   └── viewModels                            -> consists of the viewmodel implementaion related  
    │                       ├── addTodoScreenViewModel.kt            to the relevant screen   
    │                       ├── backupsScreenViewmodel.kt
    │                       ├── settingsScreenViewmodel.kt
    │                       ├── themesScreenViewModel.kt
    │                       ├── todoListScreenViewmodel.kt
    │                       ├── trashScreenViewModel.kt
    │                       ├── updateTodoViewModel.kt
    │                       └── viewTodoScreenViewModel.kt
    └── test                                                        
        └── java
            └── com
                └── app
                    └── note
                        └── ExampleUnitTest.kt                  


```


## UI

<table>
    <tr>
        <td><img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/f14972dc-7c53-4fdd-984e-0be94537a0c5" /></td>
        <td><img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/3853219f-a64c-493b-8236-61f01b5c0d60" />
</td>
    </tr>
    <tr>
        <td><img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/e907264a-412d-4e73-b861-ff09c39988d8" />
</td>
        <td><img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/2a9f6016-88e0-4579-9876-b96b4356a37b" />
</td>
    </tr>
</table>

## Installation

1. Visit the <a href="https://github.com/JDanielSecOps/Atom/releases">releases</a> page and download the 
    apk file from the latest published version

2. Click the apk from where ever downloaded in your mobile and follow the instruction for installation 

3. enjoy ;)

## Contributions

For contributions,features or issues kindly open a pull request or issue