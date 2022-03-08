package com.parsadehghan.data.remote

import com.parsadehghan.domain.entity.Weather

interface RemoteDataSource {
    suspend fun getCurrentWeather(cityId: Int): Weather

    suspend fun getMultipleDaysWeather(cityId: Int, cnt: Int): List<Weather>

    suspend fun getMultipleTimesWeather(cityId: Int, cnt: Int): List<Weather>
}