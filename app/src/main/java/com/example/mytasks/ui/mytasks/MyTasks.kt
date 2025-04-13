package com.example.mytasks.ui.mytasks

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mytasks.ui.theme.MyTasksTheme

@Composable
fun MyTasks(){
    Column {
        NewTaskControl()
        TaskList(
            tasks = fakeTasks,
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