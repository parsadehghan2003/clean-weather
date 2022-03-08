package com.parsadehghan.data

import com.parsadehghan.data.local.LocalDataSource
import com.parsadehghan.data.remote.RemoteDataSource
import com.parsadehghan.domain.entity.Weather
import com.parsadehghan.inteactor.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    override suspend fun getCurrentWeather(cityId: Int): Weather {
        return remoteDataSource.getCurrentWeather(cityId)
    }

    override suspend fun getMultipleDaysWeather(cityId: Int, cnt: Int): List<Weather> {
        return remoteDataSource.getMultipleDaysWeather(cityId, cnt)
    }

    override suspend fun getMultipleTimesWeather(cityId: Int, cnt: Int): List<Weather> {
        return remoteDataSource.getMultipleTimesWeather(cityId, cnt)
    }

}