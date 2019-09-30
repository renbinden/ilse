package uk.co.renbinden.ilse.event

import kotlin.reflect.KClass


object Events {

    private val listeners = mutableMapOf<KClass<out Event>, MutableList<(Event) -> Unit>>()

    fun <E: Event> addListener(event: KClass<E>, listener: (E) -> Unit) {
        val eventListeners = listeners[event] as? MutableList<(E) -> Unit> ?: mutableListOf()
        eventListeners.add(listener)
        listeners[event] = eventListeners as MutableList<(Event) -> Unit>
    }

    fun onEvent(event: Event) {
        listeners[event::class]?.forEach { listener -> listener(event) }
    }

}