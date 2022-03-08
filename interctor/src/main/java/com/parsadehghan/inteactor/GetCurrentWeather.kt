package com.parsadehghan.inteactor

import com.parsadehghan.domain.entity.Weather
import com.parsadehghan.domain.exceptions.IErrorHandler
import com.parsadehghan.domain.exceptions.InvalidCityId
import com.parsadehghan.inteactor.base.UseCase
import javax.inject.Inject

class GetCurrentWeather @Inject constructor(private val repository: Repository, errorHandler: IErrorHandler) :
    UseCase<Weather, Int>(errorHandler) {

    override suspend fun run(params: Int?): Weather {
        if (params == null)
            throw InvalidCityId()
        else
            return repository.getCurrentWeather(params)
    }


}