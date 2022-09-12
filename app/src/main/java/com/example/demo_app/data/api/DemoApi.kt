package com.example.demo_app.data.api

import com.example.demo_app.data.model.DemoResponse
import retrofit2.http.GET

interface DemoApi {

    @GET("/v3/c4ab4c1c-9a55-4174-9ed2-cbbe0738eedf")
    suspend fun getData(): DemoResponse
}