package com.example.mytasks.ui.mytasks

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.UUID

class MyTasksViewModel: ViewModel() {
    private val _tasks = mutableStateListOf<Task>()
    val tasks: List<Task>
        get() = _tasks

    private var _selectedTaskText: MutableState<String> = mutableStateOf<String>("")
    val selectedTaskText: String
        get() = _selectedTaskText.value

    fun changeChecked(task:Task, newValue:Boolean){
        _tasks.find {it.id == task.id}?.let { task ->
            task.checked = newValue
        }
    }

    fun addTask(task:Task){
        _tasks.add(task)
    }

    fun removeTask(task:Task){
        _tasks.remove(task)
    }

    fun getTask(id: UUID): Task? {
        val task:Task? = _tasks.find{ it.id == id }
        return task
    }

    fun selectTask(id: UUID){
        _selectedTaskText.value = _tasks.find{it.id == id}?.text ?: ""
    }

    fun unselectTask(){
        _selectedTaskText.value = ""
    }
}