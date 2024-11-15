package dev.tonyowen.spacex.network.utils

import retrofit2.Response

sealed class NetworkResponse<T>(
    val data: T? = null,
    val error: ApiError? = null,
) {
    class Success<T>(data: T?) : NetworkResponse<T>(data = data)
    class Failure<T> (code: Int, error: String) : NetworkResponse<T>(error = ApiError(code = code, message = error))
    class Loading<T> () : NetworkResponse<T>()
}

data class ApiError(
    val code: Int,
    val message: String,
)

fun <T, Y> Response<T>.foldIntoNetworkResponse(mapper: (T?) -> Y): NetworkResponse<Y> {
    return if (this.isSuccessful) {
        NetworkResponse.Success(data = mapper(this.body()))
    } else {
        NetworkResponse.Failure(code = this.code(), error = this.errorBody().toString())
    }
}