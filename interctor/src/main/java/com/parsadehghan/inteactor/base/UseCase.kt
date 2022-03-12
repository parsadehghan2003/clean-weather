package com.parsadehghan.inteactor.base

import com.parsadehghan.domain.exceptions.IErrorHandler
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class UseCase<Params,Response>(private val errorHandler: IErrorHandler) {


    abstract suspend fun run(params: Params? = null): UseCaseCallback<Response>

    suspend fun call(
        params: Params? = null,

    ) : Flow<UseCaseCallback<Response>> = flow {
        try {
            val result = run(params)
            emit(result)
            println("$TAG Response: $result")
        } catch (e: CancellationException) {
            println("$TAG Error: $e")
        } catch (e: Exception) {
            println("$TAG Error:$e cause: ${e.cause}")
        }
    }

    companion object {
        private val TAG = UseCase::class.java.name
    }

}