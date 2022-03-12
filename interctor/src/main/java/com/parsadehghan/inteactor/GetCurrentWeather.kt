package com.parsadehghan.inteactor

import com.parsadehghan.domain.entity.Weather
import com.parsadehghan.domain.exceptions.IErrorHandler
import com.parsadehghan.domain.exceptions.InvalidCityId
import com.parsadehghan.inteactor.base.UseCase
import com.parsadehghan.inteactor.base.UseCaseCallback
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentWeather @Inject constructor(private val repository: Repository, errorHandler: IErrorHandler) :
    UseCase<Int,Weather>(errorHandler) {

    override suspend fun run(params: Int?): UseCaseCallback<Weather> {
        if (params == null)
            throw InvalidCityId()
        else
            return UseCaseCallback.Success(repository.getCurrentWeather(params))
    }


}