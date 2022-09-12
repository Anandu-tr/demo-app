package com.example.demo_app.data.repository

import com.example.demo_app.data.model.DemoResponse
import com.example.demo_app.utils.Resource

interface Repository {

    suspend fun getData() : Resource<DemoResponse>
}