package cz.vratislavjindra.nba.common.coroutines.domain

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<in I, out O> constructor() {

    abstract suspend operator fun invoke(input: I): Flow<O>
}

abstract class UnitFlowUseCase<out O> constructor() {

    abstract suspend operator fun invoke(): Flow<O>
}
