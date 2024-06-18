package com.example.weatherapp2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp2.api.Constant
import com.example.weatherapp2.api.NetworkResponse
import com.example.weatherapp2.api.Retrofitinstance
import com.example.weatherapp2.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewmodel : ViewModel() {

     private val weatherapi = Retrofitinstance.weatherApi     // use in this because we make instance in retrofitinstance
     private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()

     val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult    // so we can display or use in weatherpage
                // we get result in this weatherResult

     fun getData(city :String){   // after clicking in the search icon

         _weatherResult.value= NetworkResponse.Loading

          // getWeather is a suspend fun it will take time so use launch

          viewModelScope.launch {
              val response= weatherapi.getWeather(Constant.apikey,city)                    // city we get form the search bar and api key from constant class

               try {
                   if (response.isSuccessful){     // predefined method
                       //Log.i("Response :",response.body().toString())           // to see in logcat if we get response or erro

                       // now we creat network respones class if sussess or error or loading

                       response.body()?.let {       // if it has body
                           _weatherResult.value = NetworkResponse.Success(it)

                       }
                   }
                   else{
                       //Log.i("Error :",response.message().toString())           // from this we can identify in this case we get error of internet permisson so we have to add in manifest

                       _weatherResult.value = NetworkResponse.Error("Failed to load data")
                   }
               }
               catch (e : Exception){
                   _weatherResult.value = NetworkResponse.Error("Failed to load data")
               }


          }


     }


}