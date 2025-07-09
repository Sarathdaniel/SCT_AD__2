package com.example.todolist



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

    val tasks = MutableLiveData<List<String>>(emptyList())

    fun addTask(task: String) {
        val updated = tasks.value.orEmpty().toMutableList()
        updated.add(task)
        tasks.value = updated
    }

    fun updateTask(index: Int, newTask: String) {
        val updated = tasks.value.orEmpty().toMutableList()
        updated[index] = newTask
        tasks.value = updated

    }

    fun deleteTask(index: Int) {
        val updated = tasks.value.orEmpty().toMutableList()
        updated.removeAt(index)
        tasks.value = updated
    }
}