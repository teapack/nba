package cz.vratislavjindra.nba.common.architecture.domain

abstract class UseCase<in I, out O> constructor() {

    abstract operator fun invoke(input: I): O
}

abstract class UnitUseCase<out O> constructor() {

    abstract operator fun invoke(): O
}
