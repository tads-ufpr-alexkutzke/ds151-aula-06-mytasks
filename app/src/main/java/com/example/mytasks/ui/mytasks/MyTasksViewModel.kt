package com.example.mytasks.ui.mytasks

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MyTasksViewModel: ViewModel() {
    private val _tasks = mutableStateListOf<Task>()
    val tasks: List<Task>
        get() = _tasks

    fun addTask(task:Task){
        _tasks.add(task)
    }

    fun removeTask(task:Task){
        _tasks.remove(task)
    }
}