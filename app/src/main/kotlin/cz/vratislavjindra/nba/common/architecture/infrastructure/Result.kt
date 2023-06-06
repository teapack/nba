package cz.vratislavjindra.nba.common.architecture.infrastructure

sealed class Result<T, Error> {

    class Fail<T, Error>(val error: Error) : Result<T, Error>()

    class Success<T, Error>(val data: T) : Result<T, Error>()

    fun isSuccess(): Boolean = this is Success

    companion object {

        fun <T, Error> fail(error: Error): Result<T, Error> = Fail(error = error)

        fun <T, Error> success(data: T): Result<T, Error> = Success(data = data)
    }
}

/**
 * Map Either to Either with different success type. Fail stays the same.
 */
inline fun <T, Error, NewT> Result<T, Error>.mapSuccess(
    success: (data: T) -> NewT,
): Result<NewT, Error> {
    return when (this) {
        is Result.Success -> Result.success(data = success(data))
        is Result.Fail -> Result.fail(error = error)
    }
}
