package com.example.viewmodelcounterapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    init {
        fetchTodos()
    }

    private fun fetchTodos() {
        viewModelScope.launch {
            try {
                val result = RetrofitClient.api.getTodos()
                _todos.value = result
            } catch (e: Exception) {
                // Handle error: you could emit a placeholder item with error info, or handle in UI
                _todos.value = listOf(
                    Todo(id = -1, title = "Error: ${e.message}", completed = false)
                )
            }
        }
    }
}