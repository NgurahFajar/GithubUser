package com.ngurah.githubuser.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepo {
    suspend inline fun <T> safeApiCall(crossinline body: suspend () -> T): ResultResponse<T> {
        return try {
            // blocking block
            val response = withContext(Dispatchers.IO) {
                body()
            }
            ResultResponse.Success(response)
        } catch (e: Exception) {
            ResultResponse.Error(e.message.toString())
        }
    }
}