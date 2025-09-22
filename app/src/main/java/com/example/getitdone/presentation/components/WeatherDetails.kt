package com.example.getitdone.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.getitdone.R
import com.example.getitdone.data.models.Weather
import com.example.getitdone.presentation.viewmodels.WeatherViewModel

@Composable
fun WeatherDetails(data: Weather, modifier: Modifier) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {

            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location Icon",
                modifier = Modifier.size(40.dp)
            )
            Text(text = data.location.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = data.location.country, fontSize = 17.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(16.dp))


        Text(text = data.current.condition.text, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "${data.current.temp_c}°C", fontSize = 64.sp)

        AsyncImage(
            modifier = Modifier.size(160.dp),
            model = "https:${data.current.condition.icon}".replace("64x64", "128x128"),
            contentDescription = "Weather Icon",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.storm),
            error = painterResource(R.drawable.cloudy__1_)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherKeyValue(
                        value = data.location.localTime.split(" ")[1],
                        key = "Local Time"
                    )
                    WeatherKeyValue(
                        value = data.location.localTime.split(" ")[0],
                        key = "Local Date"
                    )
                }
            }
        }


    }
}

@Composable
fun WeatherKeyValue(key: String, value: String) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = key, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)
    }
}