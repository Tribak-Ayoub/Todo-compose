package com.example.viewmodelcounterapp

import retrofit2.http.GET

interface ApiService {
    @GET("todos/1")
    suspend fun getTodo(): Todo

}