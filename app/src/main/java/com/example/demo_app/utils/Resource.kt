package com.example.demo_app.utils

class Resource<out T>(val status:Status, val data:T?, val exception: Exception?) {

    companion object{
        fun loading() = Resource(Status.LOADING, null, null)
        fun <T> success(data: T?) = Resource(Status.SUCCESS, data, null)
        fun error(e:Exception) = Resource(Status.ERROR, null, e)
    }
}

enum class Status{
    LOADING, SUCCESS, ERROR
}