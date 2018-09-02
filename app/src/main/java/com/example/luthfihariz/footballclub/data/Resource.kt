package com.example.luthfihariz.footballclub.data

data class Resource<out T>(val status: Status, val data: T?, val error: Throwable? = null) {

    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data)

        fun <T> error(error: Throwable?): Resource<T> = Resource(Status.ERROR, null, error)

        fun <T> loading(): Resource<T> = Resource(Status.LOADING, null, null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}