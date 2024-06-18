package com.example.weatherapp2.api


// created by plugin json to kotlin converter we paste a sample responce body and it will automaticall create modelclass
data class WeatherModel(
    val current: Current,
    val location: Location
)