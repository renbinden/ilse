package uk.co.renbinden.ilse.event

import kotlin.reflect.KClass

class EventMapper<T: Event>(val type: KClass<T>) {

}