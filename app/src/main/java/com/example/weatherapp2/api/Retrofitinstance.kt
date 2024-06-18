package com.example.weatherapp2.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Retrofitinstance {

    // creating instance of api using retrofit

    private const val baseUrl ="https://api.weatherapi.com";

    private fun getInstance() : Retrofit {
        return Retrofit.Builder()                         // we have to build
            .baseUrl(baseUrl)              // the url of api
            .addConverterFactory(GsonConverterFactory.create())   // we add gson convertor in the gradel file and also add retrofit
            .build()
    }

    // in this file intregated with this retrofit

    val weatherApi : WeatherApi = getInstance().create(WeatherApi::class.java)  // creating instance
                    // here is the api use
    // now we can call all the method of WeatherApi from this instance
}