package com.example.viewmodelcounterapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("todos/1")
    fun getTodo(): retrofit2.Call<Todo>

}