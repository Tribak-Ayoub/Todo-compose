package com.example.viewmodelcounterapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel: ViewModel() {
    private val _todoTitle = MutableStateFlow("Loading...")
    val todoTitle: StateFlow<String> = _todoTitle

    init {
        fetchTodo()
    }

    private fun fetchTodo() {
        viewModelScope.launch {
            try {
                val todo = RetrofitClient.api.getTodo()
                _todoTitle.value = todo.title
            } catch (e: Exception) {
                _todoTitle.value = "Error: ${e.message}"
            }
        }
    }
}