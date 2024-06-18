package com.example.weatherapp2.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// in Retrofitinstance we creat instance of api using retrofit
// here we creating api
interface WeatherApi {

    // this is the sample api call "https://api.weatherapi.com/v1/current.json?key=4c504d576e0a4d0e8be135240241306&q=London&aqi=no"
                                                                         // from here we get Query as key          from hear as q

    @GET("/v1/current.json")           // after the link of the api which we see as an example of london
    suspend fun getWeather(                          // suspend fun because asyncronise call it will take some time to get data from api
        @Query("key") apikey :String,
        @Query("q") city :String
        ) : Response<WeatherModel>           // when we call getWeather by key and q we get respose as weathermodel class

    // make WeatherModel data class on basis of sample response of api using plugin

        // if get error handle in viewmodel class
    // this is interface so we dont have to inplement we just have to define
}