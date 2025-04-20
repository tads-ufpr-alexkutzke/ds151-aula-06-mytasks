package com.example.mytasks.ui.mytasks

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mytasks.ui.theme.MyTasksTheme

@Composable
fun NewTaskControl(
    onValueChange: (text:String) -> Unit = {text -> Unit},
    onClick: () -> Unit = {},
    value: String = "",
)
{
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(10.dp)
    ) {
        OutlinedTextField(
            value=value,
            onValueChange = onValueChange,
            label = { Text("New Task") },
            modifier = Modifier
                .weight(1f)
        )
        IconButton(
            onClick = onClick,
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Insert new task")
        }
    }
}

@Preview
@Composable
fun NewTaskControlPreview(){
    MyTasksTheme {
        NewTaskControl()
    }
}
