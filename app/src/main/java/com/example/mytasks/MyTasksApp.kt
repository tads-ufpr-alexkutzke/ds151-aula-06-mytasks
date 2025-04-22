package com.example.mytasks

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import com.example.mytasks.ui.mytasks.MyTasksViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mytasks.ui.mytasks.MyTasks
import com.example.mytasks.ui.mytasks.Task
import com.example.mytasks.ui.theme.MyTasksTheme
import java.util.UUID
import java.util.logging.Logger
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTasksAppBar(
    modifier: Modifier = Modifier,
    currentScreen: String = "taskList",
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit = {},
    title:String = "",
){
    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar"
                    )
                }
            }
        }
    )
}

@Composable
fun TaskDetails(
    task: Task,
){
    Column {
        Text(text = task.text)
    }
}

@OptIn(ExperimentalUuidApi::class)
@Composable
fun MyTasksApp(
    myTasksViewModel: MyTasksViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry.value?.destination?.route ?: "taskList"
    Scaffold(
        topBar = {
            MyTasksAppBar(
                canNavigateBack = currentScreen != "taskList",
                navigateUp = {
                    navController.navigateUp()
                             },
                title = if (myTasksViewModel.selectedTaskText != "") myTasksViewModel.selectedTaskText else "My Tasks App"
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "taskList",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            composable("taskList"){
                myTasksViewModel.unselectTask()
                MyTasks(
                    myTasksViewModel = myTasksViewModel,
                    onGotoDetailsClick = { task ->
                       navController.navigate("task/${task.id.toString()}")
                    }
                )
            }
            composable("task/{taskId}") { backStackEntry ->
                val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
                val id: UUID = UUID.fromString(taskId)
                myTasksViewModel.selectTask(id)
                TaskDetails(
                    task = myTasksViewModel.getTask(id) ?: Task(text = "", initialChecked = false)
                )
            }
        }
    }
}

@Preview
@Composable
fun MyTasksAppPreview(){
    MyTasksTheme {
        MyTasksApp()
    }
}
