package com.parsadehghan.data.mapper

import com.parsadehghan.data.remote.model.Clouds
import com.parsadehghan.data.remote.model.Coord
import com.parsadehghan.data.remote.model.CurrentWeatherResponse
import com.parsadehghan.data.remote.model.Main

import org.junit.Test

class CurrentWeatherMapperKtTest {

    @Test
    fun mapToWeather() {
        val weatherList:MutableList<com.parsadehghan.data.remote.model.Weather> = mutableListOf()
        val weather:com.parsadehghan.data.remote.model.Weather = com.parsadehghan.data.remote.model.Weather("broken clouds","04d",803,"Clouds")
        weatherList.add(weather)

        val clouds = Clouds(1)
        val currentWeatherResponse = CurrentWeatherResponse("asdasd",clouds ,200,
            Coord(35.6944,51.4215),1646838000,803, Main(123.1,123,1123,12.99,14.97,12.99),
            "Tehran",null,12600,1,weatherList,null
        )
        assert(mapToWeather(currentWeatherResponse).max == 15)

    }
}