package cz.vratislavjindra.nba.common.architecture.infrastructure

import cz.vratislavjindra.nba.R

sealed class AppError(val errorMessageResId: Int) {

    sealed class Api(errorMessageResId: Int) : AppError(errorMessageResId = errorMessageResId) {

        object EmptyResponse : Api(errorMessageResId = R.string.error_api_empty_response)

        data class HttpError(
            val errorType: ErrorType,
            val responseErrorMessage: String?,
        ) : Api(errorMessageResId = errorType.errorMessageResId) {

            enum class ErrorType(val errorCode: Int?, val errorMessageResId: Int) {

                BadRequest(
                    errorCode = 400,
                    errorMessageResId = R.string.error_api_http_bad_request,
                ),
                NotFound(
                    errorCode = 404,
                    errorMessageResId = R.string.error_api_http_not_found,
                ),
                NotAcceptable(
                    errorCode = 406,
                    errorMessageResId = R.string.error_api_http_not_acceptable,
                ),
                TooManyRequests(
                    errorCode = 429,
                    errorMessageResId = R.string.error_api_http_too_many_requests,
                ),
                InternalServerError(
                    errorCode = 500,
                    errorMessageResId = R.string.error_api_http_internal_server_error,
                ),
                ServiceUnavailable(
                    errorCode = 503,
                    errorMessageResId = R.string.error_api_http_service_unavailable,
                ),
                Unknown(
                    errorCode = null,
                    errorMessageResId = R.string.error_api_http_unknown,
                );

                companion object {

                    fun fromErrorCode(errorCode: Int?): ErrorType {
                        return values().find { it.errorCode == errorCode } ?: Unknown
                    }
                }
            }
        }
    }

    object NullId : AppError(errorMessageResId = R.string.error_null_id)

    object Unknown : AppError(errorMessageResId = R.string.error_unknown)
}
