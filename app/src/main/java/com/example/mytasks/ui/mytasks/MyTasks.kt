package com.example.mytasks.ui.mytasks

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.mytasks.ui.theme.MyTasksTheme

@Composable
fun MyTasks(){
    var newTaskText by remember { mutableStateOf("") }
    var tasks = remember { mutableStateListOf<Task>() }
    Column {
        NewTaskControl(
            onValueChange = {text -> newTaskText = text},
            value = newTaskText,
            onClick = { tasks.add(Task(text=newTaskText, initialChecked = false)) }
        )
        TaskList(
            tasks = tasks.sortedWith(compareBy({ it.checked }, { it.text })),
            onCheckedChange = { task, newValue -> task.checked = newValue },
            onRemoveClick = { task -> tasks.remove(task) }
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