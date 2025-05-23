package com.example.mytasks.ui.mytasks

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mytasks.ui.theme.MyTasksTheme

@Composable
fun MyTasks(
    myTasksViewModel: MyTasksViewModel = viewModel(),
    onGotoDetailsClick: (Task) -> Unit = {}
){
    var newTaskText by rememberSaveable { mutableStateOf("") }

    Column {
        NewTaskControl(
            onValueChange = {text -> newTaskText = text},
            value = newTaskText,
            onClick = { myTasksViewModel.addTask(Task(text=newTaskText, initialChecked = false)) }
        )
        TaskList(
            tasks = myTasksViewModel.tasks.sortedWith(compareBy({ it.checked }, { it.text })),
            onCheckedChange = { task, newValue -> myTasksViewModel.changeChecked(task,newValue) },
            onRemoveClick = { task -> myTasksViewModel.removeTask(task) },
            onEditClick = { task ->
                task.edit = true
                task.editText = task.text
            },
            onEditTextChange = { task, newText -> task.editText = newText},
            onGotoDetailsClick = { task -> onGotoDetailsClick(task) }
        )
    }
}

@Preview
@Composable
fun MyTasksPreview(){
    MyTasksTheme {
        MyTasks()
    }
}