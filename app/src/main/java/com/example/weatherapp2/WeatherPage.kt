package com.example.weatherapp2

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.example.weatherapp2.api.NetworkResponse
import com.example.weatherapp2.api.WeatherModel


@Composable
fun WeatherPage(viewmodel: WeatherViewmodel){

    var city by remember { mutableStateOf("") }

    val weatherResult = viewmodel.weatherResult.observeAsState()

    val backgroundImage: Painter = painterResource(id = R.drawable._121407)

    val keyborardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop // Crop the image to fill the available space
        )

        // Content inside the Box
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent) // Optional: Set a background color behind the image
        ) {
            // for backgound image


            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Spacer(modifier = Modifier.size(35.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Spacer(modifier = Modifier.width(20.dp))

//            OutlinedTextField(
//                value = city,
//                onValueChange ={ city=it },
//                label = { Text(text = "Search for any location") },
//                modifier = Modifier.weight(1f), // so it take only the space in the box not take the space of the icon
//
//            )
                    TextField(
                        value = city,
                        onValueChange = { city = it
                           // viewmodel.getData(city)
                            },
                        placeholder = { Text(text = "Search for any location") },
                        shape = RoundedCornerShape(16.dp),
                        textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.sanf))),
                        modifier = Modifier.weight(1f),
                        colors = TextFieldDefaults.colors( // textColor = Color.Transparent, // not contain any parameter like this in this fun // placeholderColor = Color.Transparent, // Set the placeholder color to transparent
                            focusedIndicatorColor = Color.Transparent, // Remove underline on focus
                            unfocusedIndicatorColor = Color.Transparent // Remove underline on unfocus
                        )
                    )

                    IconButton(onClick = {
                        viewmodel.getData(city)
                        keyborardController?.hide()

                    }, modifier = Modifier.size(50.dp)) {

                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )

                    }


                }

                when (val result = weatherResult.value) {
                    is NetworkResponse.Error -> {
                        Text(text = result.message)
                    }

                    NetworkResponse.Loading -> {
                        CircularProgressIndicator()
                    }

                    is NetworkResponse.Success -> {
//                Text(text = result.data.toString())
                        Weatherdetailes(data = result.data)
                    }

                    null -> {}
                }
            }
        }
    }
}


@Composable
fun Weatherdetailes(data :WeatherModel){      // weathermodel contain all details what we made form plugin



            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.location1),
                        modifier = Modifier.size(36.dp),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )      // if dont want to use tint use Image composable

                    Spacer(modifier = Modifier.size(5.dp))

                    Text(
                        text = data.location.name + ",", fontSize = 27.sp,
                        modifier = Modifier.align(Alignment.Bottom),
                        fontFamily = FontFamily(Font(R.font.sanf))
                    )
                    Spacer(modifier = Modifier.size(5.dp))

                    Text(
                        text = data.location.country, fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.Bottom),
                        fontFamily = FontFamily(Font(R.font.sanf))
                    )


                }

                Spacer(modifier = Modifier.height(26.dp))

                Text(
                    text = data.current.temp_c + "°C", fontSize = 60.sp,
                    fontFamily = FontFamily(Font(R.font.sanf))
                )

                AsyncImage(
                    model = "https:${data.current.condition.icon}".replace("64x64", "128x128"),
                    modifier = Modifier
                        .size(160.dp),
                    contentDescription = null
                )

                Text(
                    text = data.current.condition.text, fontSize = 19.sp,
                    fontFamily = FontFamily(Font(R.font.sanf)),
                    color = Color.Black.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(25.dp))


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(23.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Weatherkeyvalue(data.current.humidity, "Humidity", R.drawable.humidity)
                        Weatherkeyvalue(data.current.wind_kph, "Wind Speed", R.drawable.storm)

                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        Weatherkeyvalue(
                            data.current.feelslike_c,
                            "Real Feel",
                            R.drawable.temperature
                        )
                        Weatherkeyvalue(
                            data.current.precip_in,
                            "Precipitation",
                            R.drawable.precipitation
                        )

                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        Weatherkeyvalue(data.current.uv, "U.V.", R.drawable.uv_protection)
                        Weatherkeyvalue(
                            data.location.localtime.split(" ")[1],
                            "Local Time",
                            R.drawable.wall_clock
                        )

                    }


                }


            }

        }



@Composable
fun Weatherkeyvalue(value:String, type:String,img:Int){


    Box(modifier = Modifier
//        .graphicsLayer(alpha = 0.8f) // Adjust alpha for transparency
  //      .background(color = Color.Transparent.copy(alpha = 0.5f)) // Adjust alpha for transparency
//        .background(color = Color.Transparent)
        .width(146.dp)
        .height(110.dp)
        .background(
            color = Color.Black.copy(alpha = 0.13f),
            shape = RoundedCornerShape(16.dp)
        )
        .background(color = Color.Transparent),
        contentAlignment = Alignment.Center

    ){




            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    painter = painterResource(id = img),
                    modifier = Modifier.size(40.dp),
                    contentDescription = null,
                    tint = Color.Unspecified
                )


                Text(
                    text = type, fontSize = 25.sp,
                    fontFamily = FontFamily(Font(R.font.sanf))
                )



                Text(
                    text = value, fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.sanf)),
                    color = Color.Black.copy(alpha = 0.5f)
                )
            }



    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun WeatherPagepreview(){
//    Weatherdetailes(data = )
//}