package com.example.mytasks.ui.mytasks

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mytasks.ui.theme.MyTasksTheme
import java.util.UUID

data class Task(val text:String, val checked: Boolean){
    val id: UUID = UUID.randomUUID()
}

val fakeTasks: List<Task> = listOf(
    Task(text="Comprar pão", checked = false),
    Task(text="Estudar DS151", checked = false),
    Task(text="Criar app de Taferas", checked = true),
    Task(text="Arrumar a casa", checked = true),
)

@Composable
fun TaskList(
    tasks: List<Task> = emptyList<Task>(),

){
    LazyColumn {
        items(
            items = tasks,
            key = { task -> task.id }
        ) { task ->
            TaskItem(
                text = task.text,
                checked = task.checked,
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