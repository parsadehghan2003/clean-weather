package com.parsadehghan.inteactor

import com.parsadehghan.domain.entity.TimeState
import com.parsadehghan.domain.exceptions.IErrorHandler
import com.parsadehghan.inteactor.base.UseCase
import com.parsadehghan.inteactor.base.UseCaseCallback
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GetCurrentTimeState @Inject constructor(errorHandler: IErrorHandler) :
    UseCase<Void,TimeState>(errorHandler) {

    override suspend fun run(params: Void?): UseCaseCallback<TimeState> {
        val date = Date()
        val simpleDateFormat = SimpleDateFormat("HH", Locale.US)
        return when (simpleDateFormat.format(date).toInt()) {
            in 0..6 -> {
                UseCaseCallback.Success(TimeState.Dawn)
            }
            in 7..11 -> {
                UseCaseCallback.Success(TimeState.Morning)
            }
            in 12..16 -> {
                UseCaseCallback.Success(TimeState.Noon)
            }
            in 17..19 -> {
                UseCaseCallback.Success(TimeState.Evening)
            }
            in 20..23 -> {
                UseCaseCallback.Success(TimeState.Night)
            }
            else ->
                UseCaseCallback.Success(TimeState.Undefined)

        }
    }


}