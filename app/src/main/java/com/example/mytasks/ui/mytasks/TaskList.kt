package com.example.mytasks.ui.mytasks

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.mytasks.ui.theme.MyTasksTheme
import java.util.UUID

class Task(
    val text:String,
    val initialChecked: Boolean,
){
    val id: UUID = UUID.randomUUID()
    var checked by mutableStateOf(initialChecked)
}

val fakeTasks: List<Task> = listOf(
    Task(text="Comprar pão", initialChecked = false),
    Task(text="Estudar DS151", initialChecked = false),
    Task(text="Criar app de Taferas", initialChecked = true),
    Task(text="Arrumar a casa", initialChecked = true),
)

@Composable
fun TaskList(
    tasks: List<Task> = emptyList<Task>(),
    onRemoveClick: (Task) -> Unit = {task -> Unit},
    onCheckedChange: (Task, Boolean) -> Unit = {task, newValue -> Unit},
){
    LazyColumn {
        items(
            items = tasks,
            key = { task -> task.id }
        ) { task ->
            TaskItem(
                text = task.text,
                checked = task.checked,
                onRemoveClick = { onRemoveClick(task) },
                onCheckedChange = { newValue -> onCheckedChange(task, newValue) }
            )
        }
    }

}

@Preview
@Composable
fun TaskListPreview(){
    MyTasksTheme {
        TaskList()
    }
}