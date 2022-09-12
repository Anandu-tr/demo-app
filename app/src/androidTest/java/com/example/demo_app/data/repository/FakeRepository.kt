package com.example.demo_app.data.repository

import com.example.demo_app.data.model.DemoResponse
import com.example.demo_app.data.model.RowData
import com.example.demo_app.utils.Resource

class FakeRepository : Repository {

    private var returnError = false

    fun setReturnError(value: Boolean){
        returnError = value
    }
    override suspend fun getData(): Resource<DemoResponse> {
        if (returnError)
            return Resource.error(Exception("Test error"))
        return Resource.success(DemoResponse("Test", listOf(
            RowData("Test 1", "", ""),
            RowData("Test 2", "", ""),
        )))
    }

}