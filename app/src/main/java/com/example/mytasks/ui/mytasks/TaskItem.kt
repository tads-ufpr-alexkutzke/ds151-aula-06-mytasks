package com.example.mytasks.ui.mytasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mytasks.ui.theme.MyTasksTheme
import java.nio.file.WatchEvent

@Composable
fun TaskItem(
    text:String = "TaskItem",
    checked: Boolean = false,
    enabled: Boolean = true,
    onCheckedChange: (checked: Boolean) -> Unit = {checked -> Unit},
    onRemoveClick: () -> Unit = {}
){
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
            )
            Text(
                text=text,
                modifier = Modifier.weight(1f),
                textDecoration = if(checked) TextDecoration.LineThrough else TextDecoration.None
            )
            IconButton(
                onClick = onRemoveClick,
            ) {
                Icon(Icons.Filled.Close, contentDescription = "Remove")
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
        TaskItem(checked = true)
    }
}

@Preview
@Composable
fun ListTaskItemPreview(){
    MyTasksTheme {
        Column {
            TaskItem(checked = true)
            TaskItem(checked = false)
            TaskItem(checked = true)
        }
    }
}
