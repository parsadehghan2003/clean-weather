package com.parsadehghan.inteactor.base

import com.parsadehghan.domain.exceptions.ErrorModel

interface UseCaseCallback<Type> {

    fun onSuccess(result: Type)

    fun onError(errorModel: ErrorModel?)
}