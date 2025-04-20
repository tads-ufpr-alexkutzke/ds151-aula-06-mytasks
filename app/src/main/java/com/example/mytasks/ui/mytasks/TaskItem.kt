package com.example.mytasks.ui.mytasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mytasks.ui.theme.MyTasksTheme


@Composable
fun TaskItem(
    task: Task = Task(text="New Item", initialChecked = false),
    enabled: Boolean = true,
    onCheckedChange: (checked: Boolean) -> Unit = {checked -> Unit},
    onRemoveClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onConfirmEditClick: () -> Unit = {},
    onEditTextChange: (String) -> Unit = {},
){
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
            )
            if(!task.edit) {
                Text(
                    text = task.text,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(enabled = !task.checked, onClick = onEditClick),
                    textDecoration = if (task.checked) TextDecoration.LineThrough else TextDecoration.None,
                )
                IconButton(
                    onClick = onRemoveClick,
                    enabled = enabled
                ) {
                    Icon(Icons.Filled.Close, contentDescription = "Remove")
                }
            }
            else {
                BasicTextField(
                    value = task.editText,
                    onValueChange = onEditTextChange,
                    modifier = Modifier
                        .weight(1f)
                    )
                IconButton(
                    onClick = onConfirmEditClick,
                ) {
                    Icon(Icons.Filled.Check, contentDescription = "Confirm Edit")
                }
            }
        }
        Row(modifier = Modifier.padding(horizontal = 20.dp)){
            HorizontalDivider(thickness = 1.dp, modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
fun TaskItemPreview(){
    MyTasksTheme {
        TaskItem()
    }
}

@Preview
@Composable
fun CheckedTaskItemPreview(){
    MyTasksTheme {
        TaskItem(task=Task(text="Teste", initialChecked = true, initialEdit = true))
    }
}

@Preview
@Composable
fun ListTaskItemPreview(){
    MyTasksTheme {
        Column {
            TaskItem(task=Task(text="Teste 1", initialChecked = true))
            TaskItem(task=Task(text="Teste 2", initialChecked = false, initialEdit = true))
            TaskItem(task=Task(text="Teste 3", initialChecked = true))
        }
    }
}
