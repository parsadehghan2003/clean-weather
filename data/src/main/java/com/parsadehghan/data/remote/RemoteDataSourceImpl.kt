package com.parsadehghan.data.remote

import android.content.Context
import com.parsadehghan.data.mapper.mapToDailyForecast
import com.parsadehghan.data.mapper.mapToForecast
import com.parsadehghan.data.mapper.mapToWeather
import com.parsadehghan.domain.Constants
import com.parsadehghan.domain.entity.Weather
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

public class RemoteDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val service: OpenWeatherApiService
) : RemoteDataSource {

    companion object {
    }

    override suspend fun getCurrentWeather(cityId: Int): Weather {
        val currentWeather = service.getCurrentWeather(
            cityId = cityId,
            units = "metric",
            appId = Constants.APP_TOKEN
        )

        return mapToWeather(currentWeather)
    }

    override suspend fun getMultipleDaysWeather(cityId: Int, cnt: Int): List<Weather> {
        val multipleDaysWeather = service.getMultipleDaysWeather(
            cityId,
            "metric",
            "en",
            cnt,
            Constants.APP_TOKEN
        )

        return mapToDailyForecast(multipleDaysWeather)
    }

    override suspend fun getMultipleTimesWeather(cityId: Int, cnt: Int): List<Weather> {
        val multipleTimesWeather = service.getMultipleTimesWeather(
            cityId,
            "metric",
            "en",
            cnt,
            Constants.APP_TOKEN
        )

        return mapToForecast(multipleTimesWeather)
    }

}