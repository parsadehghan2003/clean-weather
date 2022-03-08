package com.parsadehghan.inteactor

import com.parsadehghan.domain.entity.RequestMultipleWeather
import com.parsadehghan.domain.entity.Weather
import com.parsadehghan.domain.exceptions.IErrorHandler
import com.parsadehghan.domain.exceptions.InvalidCityId
import com.parsadehghan.domain.exceptions.InvalidRequestMultipleWeather
import com.parsadehghan.inteactor.base.UseCase
import javax.inject.Inject

class GetMultipleTimesWeather @Inject constructor(
    private val repository: Repository,
    errorHandler: IErrorHandler
) : UseCase<List<Weather>, RequestMultipleWeather>(errorHandler) {

    override suspend fun run(params: RequestMultipleWeather?): List<Weather> {
        when {
            params == null -> throw InvalidRequestMultipleWeather()
            params.cityId == null -> throw InvalidCityId()
            else -> return repository.getMultipleTimesWeather(params.cityId!!, params.cnt)
        }
    }

}