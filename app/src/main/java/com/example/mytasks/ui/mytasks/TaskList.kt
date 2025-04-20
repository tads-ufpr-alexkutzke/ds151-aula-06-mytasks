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
    var text:String,
    val initialChecked: Boolean,
    val initialEdit: Boolean = false,
){
    val id: UUID = UUID.randomUUID()
    var checked by mutableStateOf(initialChecked)
    var editText by mutableStateOf("")
    var edit by mutableStateOf(initialEdit)
}

val fakeTasks: List<Task> = listOf(
    Task(text="Comprar pão", initialChecked = false),
    Task(text="Estudar DS151", initialChecked = false, initialEdit = true),
    Task(text="Criar app de Taferas", initialChecked = true),
    Task(text="Arrumar a casa", initialChecked = true),
)

@Composable
fun TaskList(
    tasks: List<Task> = fakeTasks,
    onRemoveClick: (Task) -> Unit = {},
    onEditClick: (Task) -> Unit = {},
    onCheckedChange: (Task, Boolean) -> Unit = {task, newValue -> Unit},
    onEditTextChange: (Task, String) -> Unit = {task, newText -> Unit}
){
    LazyColumn {
        items(
            items = tasks,
            key = { task -> task.id }
        ) { task ->
            TaskItem(
                task = task,
                enabled = !task.edit,
                onRemoveClick = { onRemoveClick(task) },
                onEditClick = { onEditClick(task) },
                onCheckedChange = { newValue -> onCheckedChange(task, newValue) },
                onEditTextChange = { newText:String -> onEditTextChange(task, newText) },
                onConfirmEditClick = {
                    task.edit = false
                    task.text = task.editText
                }
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

@Preview
@Composable
fun TaskListEditingPreview(){
    MyTasksTheme {
        TaskList()
    }
}
