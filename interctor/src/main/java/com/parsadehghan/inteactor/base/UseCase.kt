package com.parsadehghan.inteactor.base

import com.parsadehghan.domain.exceptions.IErrorHandler
import kotlinx.coroutines.CancellationException

abstract class UseCase<Response, Params>(private val errorHandler: IErrorHandler) {


    abstract suspend fun run(params: Params? = null): Response

    suspend fun call(
        params: Params? = null,
        onResult: (UseCaseCallback<Response>)?
    ) {
        try {
            val result = run(params)
            onResult?.onSuccess(result)
            println("$TAG Response: $result")
        } catch (e: CancellationException) {
            println("$TAG Error: $e")
            onResult?.onError(errorHandler.handleException(e))
        } catch (e: Exception) {
            println("$TAG Error:$e cause: ${e.cause}")
            onResult?.onError(errorHandler.handleException(e))
        }
    }

    companion object {
        private val TAG = UseCase::class.java.name
    }

}