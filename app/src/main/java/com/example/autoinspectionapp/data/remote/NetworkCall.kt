package com.example.autoinspectionapp.data.remote

import com.example.autoinspectionapp.data.remote.sealed.ResultNew
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class NetworkCall {

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        apiCall: suspend () -> T
    ): ResultNew<T> {
        return withContext(dispatcher) {
            try {
                val result: T = retryIO { apiCall.invoke() }
                ResultNew.Success(result)
            } catch (e: Exception) {
                @Suppress("UNCHECKED_CAST")
                handleException(e) as ResultNew<T>
            }
        }
    }

    protected fun handleException(throwable: Exception): ResultNew<GenericErrorResponse> {
        return when (throwable) {
            is HttpException -> {
                handleHttpException(throwable)
            }

            is SocketTimeoutException -> {
                ResultNew.Error(
                    GenericErrorResponse(
                        errors = Errors(emptyList(), emptyList(), emptyList(), emptyList()),
                        message = "Unstable internet connection!",
                        errorType = "Timeout",
                        path = "",
                        statusCode = 500
                    )
                )
            }

            is IOException -> {
                // No connectivity or connection lost
                ResultNew.Error(
                    GenericErrorResponse(
                        errors = Errors(emptyList(), emptyList(), emptyList(), emptyList()),
                        message = "No internet connection.",
                        errorType = "Network Error",
                        path = "",
                        statusCode = 0
                    )
                )
            }

            else -> {
                ResultNew.Error(
                    GenericErrorResponse(
                        errors = Errors(emptyList(), emptyList(), emptyList(), emptyList()),
                        message = "An unexpected error occurred. Please try again later.",
                        errorType = "Unknown",
                        path = "",
                        statusCode = 500
                    )
                )
            }
        }
    }

    private fun handleHttpException(httpException: HttpException): ResultNew<GenericErrorResponse> {
        val responseCode = httpException.code()
        val responseBody = httpException.response()?.errorBody()?.string()

        return if (responseBody != null) {
            try {
                val jsonObject = JSONObject(responseBody)
                val errorResponse = convertErrorBody(jsonObject)
                ResultNew.Error(errorResponse)
            } catch (_: JSONException) {
                ResultNew.Error(
                    GenericErrorResponse(
                        errors = Errors(emptyList(), emptyList(), emptyList(), emptyList()),
                        message = "Error parsing server response: ${httpException.message()}",
                        errorType = "Parsing Error",
                        path = httpException.response()?.raw()?.request?.url.toString(),
                        statusCode = responseCode
                    )
                )
            }
        } else {
            ResultNew.Error(
                GenericErrorResponse(
                    errors = Errors(emptyList(), emptyList(), emptyList(), emptyList()),
                    message = "Couldn't reach the server. Please check your internet connection.",
                    errorType = "Network Error",
                    path = "",
                    statusCode = responseCode
                )
            )
        }
    }

    private fun convertErrorBody(jObjError: JSONObject): GenericErrorResponse {
        return try {
            Gson().fromJson(jObjError.toString(), GenericErrorResponse::class.java)
        } catch (_: Exception) {
            GenericErrorResponse(
                errors = Errors(emptyList(), emptyList(), emptyList(), emptyList()),
                message = "Error parsing error response.",
                errorType = "Parsing Error",
                path = "",
                statusCode = 400
            )
        }
    }

    suspend fun <T> retryIO(
        times: Int = 3,
        initialDelay: Long = 1000,
        maxDelay: Long = 4000,
        factor: Double = 2.0,
        block: suspend () -> T
    ): T {
        var currentDelay = initialDelay
        repeat(times - 1) {
            try {
                return block()
            } catch (e: IOException) {
                delay(currentDelay)
                currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
            }
        }
        return block()
    }
}


