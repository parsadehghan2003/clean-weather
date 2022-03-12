package com.parsadehghan.inteactor

import com.parsadehghan.domain.entity.RequestMultipleWeather
import com.parsadehghan.domain.entity.Weather
import com.parsadehghan.domain.exceptions.IErrorHandler
import com.parsadehghan.domain.exceptions.InvalidCityId
import com.parsadehghan.domain.exceptions.InvalidRequestMultipleWeather
import com.parsadehghan.inteactor.base.UseCase
import com.parsadehghan.inteactor.base.UseCaseCallback
import javax.inject.Inject

class GetMultipleDaysWeather @Inject constructor(
    private val repository: Repository,
    errorHandler: IErrorHandler
) :
    UseCase<RequestMultipleWeather,List<Weather>>(errorHandler) {

    override suspend fun run(params: RequestMultipleWeather?): UseCaseCallback<List<Weather>> {
        when {
            params == null -> throw InvalidRequestMultipleWeather()
            params.cityId == null -> throw InvalidCityId()
            else -> return UseCaseCallback.Success(repository.getMultipleDaysWeather(params.cityId!!, params.cnt))
        }
    }


}