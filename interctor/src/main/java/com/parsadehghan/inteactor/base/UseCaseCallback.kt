package com.parsadehghan.inteactor.base

import com.parsadehghan.domain.exceptions.ErrorModel

sealed class UseCaseCallback<T> (
    val data: T? = null,
    val errorModel: ErrorModel? = null
) {

    class Success<T>(data: T) : UseCaseCallback<T>(data)
    class Error<T>(errorModel: ErrorModel?, data: T? = null) : UseCaseCallback<T>(data, errorModel)
}