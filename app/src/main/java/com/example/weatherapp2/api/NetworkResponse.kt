package com.example.weatherapp2.api

// T referse to Weathermodel
sealed class NetworkResponse <out T>{            // sealed class because it will take time to get data and it is a asyncronise class
                           // so we wrap it with anything

    data class Success<out T>(val data : T): NetworkResponse<T>()
    data class Error(val message : String): NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()




}