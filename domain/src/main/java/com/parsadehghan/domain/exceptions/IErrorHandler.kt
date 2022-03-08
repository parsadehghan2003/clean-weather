package com.parsadehghan.domain.exceptions

interface IErrorHandler {
    fun handleException(throwable: Throwable?): ErrorModel
}