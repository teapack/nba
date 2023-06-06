package cz.vratislavjindra.nba.common.network.data

import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.architecture.infrastructure.Result
import retrofit2.Response

fun <T, NewT> Response<T>.handleResponse(
    mapSuccess: (T) -> NewT,
): Result<NewT, AppError> {
    return if (isSuccessful) {
        val data = body()
        if (data != null) {
            Result.success(data = mapSuccess(data))
        } else {
            Result.fail(error = AppError.Api.EmptyResponse)
        }
    } else {
        Result.fail(
            error = AppError.Api.HttpError(
                errorType = AppError.Api.HttpError.ErrorType.fromErrorCode(errorCode = code()),
                responseErrorMessage = errorBody()?.string(),
            ),
        )
    }
}
