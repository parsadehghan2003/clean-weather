package com.parsadehghan.data.mapper

import com.parsadehghan.data.remote.model.*
import com.parsadehghan.domain.entity.WeatherState
import org.junit.Assert.*

import org.junit.Test
import kotlin.math.roundToInt

class MultipleWeatherMapperKtTest {

    @Test
    fun mapToForecast() {
        val city = City(Coord(35.6944,51.4215),"IR",112931,"Tehran",0,12600)
        val list = mutableListOf<ForecastResult>()
        val weatherList:MutableList<Weather> = mutableListOf()
        val weather = Weather("04d","broken clouds",803,"Clouds")
        weatherList.add(weather)
        val forecastResult = ForecastResult(Clouds(1),1646816400,"2022-03-09 09:00:00",
            ForecastMain(11.21,880,33,1015,1015,12.99,-1.15,14.97,12.99),
            Sys("IR",112931,1646794447,1646836559,1),weatherList, Wind(157,2.17)
        )
        list.add(forecastResult)
        val forecastResponse = ForecastResponse(city,7,"200",list,0)
        assertEquals(mapToForecast(forecastResponse).first(),com.parsadehghan.domain.entity.Weather(forecastResult.main.temp_min.roundToInt(),forecastResult.main.temp_max.roundToInt(),forecastResult.main.temp.roundToInt(),WeatherState.ClearSky,"0pm"))
    }

    @Test
    fun mapToDailyForecast() {
    }
}