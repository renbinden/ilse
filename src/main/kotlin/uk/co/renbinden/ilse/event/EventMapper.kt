package uk.co.renbinden.ilse.event

import kotlin.reflect.KClass

abstract class EventMapper<T: Event>(val type: KClass<T>)