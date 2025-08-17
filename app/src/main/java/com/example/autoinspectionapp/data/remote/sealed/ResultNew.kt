package com.example.autoinspectionapp.data.remote.sealed

import com.example.autoinspectionapp.data.remote.GenericErrorResponse

sealed class ResultNew<T>(val data: T? = null, val error: GenericErrorResponse? = null) {
    class Success<T>(data: T) : ResultNew<T>(data)
    class Error<T>(error: GenericErrorResponse?, data: T? = null) : ResultNew<T>(data, error)
    // class Loading<T>(data: T? = null) : ApiResult<T>(data)
}