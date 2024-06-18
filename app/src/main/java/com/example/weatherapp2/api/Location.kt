package com.example.weatherapp2.api


// using plugin
data class Location(
    val country: String,
    val lat: String,
    val localtime: String,
    val localtime_epoch: String,           // change double or int to String because we just have to display in this case
    val lon: String,
    val name: String,
    val region: String,
    val tz_id: String
)