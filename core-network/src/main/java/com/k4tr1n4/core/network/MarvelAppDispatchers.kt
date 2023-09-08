package com.k4tr1n4.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME


@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val marvelAppDispatcher: MarvelAppDispatchers)

enum class MarvelAppDispatchers {
    IO
}