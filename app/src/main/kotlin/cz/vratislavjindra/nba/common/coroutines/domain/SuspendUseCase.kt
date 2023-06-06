package cz.vratislavjindra.nba.common.coroutines.domain

abstract class SuspendUseCase<in I, out O> constructor() {

    abstract suspend operator fun invoke(input: I): O
}

abstract class UnitSuspendUseCase<out O> constructor() {

    abstract suspend operator fun invoke(): O
}
