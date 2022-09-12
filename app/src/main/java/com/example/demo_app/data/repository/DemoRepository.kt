package com.example.demo_app.data.repository

import com.example.demo_app.data.api.DemoApi
import com.example.demo_app.data.model.DemoResponse
import com.example.demo_app.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DemoRepository(private val api: DemoApi) : Repository {

    override suspend fun getData(): Resource<DemoResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getData()
                Resource.success(response)
            }catch (e:Exception){
                Resource.error(e)
            }
        }
    }
}