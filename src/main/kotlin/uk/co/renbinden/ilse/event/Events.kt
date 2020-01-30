package uk.co.renbinden.ilse.event

import kotlin.math.abs
import kotlin.reflect.KClass


object Events {

    private val listeners = mutableMapOf<KClass<out Event>, MutableList<Listener<Event>>>()
    private val listenerFilters = mutableMapOf<Listener<out Event>, (Event) -> Boolean>()
    private val oneTimeListeners = mutableListOf<Listener<out Event>>()

    private fun unsafeAddListener(
        event: KClass<out Event>,
        filter: ((Event) -> Boolean)? = null,
        listener: Listener<Event>
    ) {
        val eventListeners = listeners[event] ?: mutableListOf()
        eventListeners.add(
            abs(eventListeners.binarySearchBy(listener.priority, selector = Listener<Event>::priority) + 1),
            listener
        )
        listeners[event] = eventListeners

        if (filter != null) {
            listenerFilters[listener] = filter
        }
    }

    fun <E: Event> addListener(
        event: KClass<E>,
        filter: ((E) -> Boolean)?,
        listener: Listener<E>
    ) {
        unsafeAddListener(event as KClass<out Event>, filter as ((Event) -> Boolean)?, listener as Listener<Event>)
    }

    fun <E: Event> addListener(
        event: KClass<E>,
        listener: Listener<E>
    ) {
        addListener(event, null, listener)
    }

    fun <E: Event> addListener(
        mapper: EventMapper<E>,
        filter: ((E) -> Boolean)?,
        listener: Listener<E>
    ) {
        addListener(mapper.type, filter, listener)
    }

    fun <E: Event> addListener(
        mapper: EventMapper<E>,
        listener: Listener<E>
    ) {
        addListener(mapper, null, listener)
    }

    fun <E: Event> addListener(
        event: KClass<E>,
        filter: ((E) -> Boolean)?,
        listener: (E) -> Unit
    ) {
        addListener(event, filter, Listener(listener, 0))
    }

    fun <E: Event> addListener(
        event: KClass<E>,
        listener: (E) -> Unit
    ) {
        addListener(event, null, listener)
    }

    fun <E: Event> addListener(
        mapper: EventMapper<E>,
        filter: ((E) -> Boolean)?,
        listener: (E) -> Unit
    ) {
        addListener(mapper.type, filter, listener)
    }

    fun <E: Event> addListener(
        mapper: EventMapper<E>,
        listener: (E) -> Unit
    ) {
        addListener(mapper, null, listener)
    }

    fun <E: Event> listenOnce(
        event: KClass<E>,
        filter: ((E) -> Boolean)?,
        listener: Listener<E>
    ) {
        oneTimeListeners.add(listener)
        addListener(event, filter, listener)
    }

    fun <E: Event> listenOnce(
        event: KClass<E>,
        listener: Listener<E>
    ) {
        listenOnce(event, null, listener)
    }

    fun <E: Event> listenOnce(
        mapper: EventMapper<E>,
        filter: ((E) -> Boolean)?,
        listener: Listener<E>
    ) {
        listenOnce(mapper.type, filter, listener)
    }

    fun <E: Event> listenOnce(
        mapper: EventMapper<E>,
        listener: Listener<E>
    ) {
        listenOnce(mapper, null, listener)
    }

    fun <E: Event> listenOnce(
        event: KClass<E>,
        filter: ((E) -> Boolean)?,
        listener: (E) -> Unit
    ) {
        listenOnce(event, filter, Listener(listener, 0))
    }

    fun <E: Event> listenOnce(
        event: KClass<E>,
        listener: (E) -> Unit
    ) {
        listenOnce(event, null, listener)
    }

    fun <E: Event> listenOnce(
        mapper: EventMapper<E>,
        filter: ((E) -> Boolean)?,
        listener: (E) -> Unit
    ) {
        listenOnce(mapper.type, filter, listener)
    }

    fun <E: Event> listenOnce(
        mapper: EventMapper<E>,
        listener: (E) -> Unit
    ) {
        listenOnce(mapper, null, listener)
    }

    private fun unsafeRemoveListener(
        event: KClass<out Event>,
        listener: Listener<out Event>
    ) {
        val eventListeners = listeners[event]
        if (eventListeners != null) {
            eventListeners.remove(listener)
            if (eventListeners.isEmpty()) {
                listeners.remove(event)
            } else {
                listeners[event] = eventListeners
            }
        }

        listenerFilters.remove(listener)

        oneTimeListeners.remove(listener)
    }

    fun <E: Event> removeListener(
        event: KClass<E>,
        listener: Listener<E>
    ) {
        unsafeRemoveListener(event as KClass<out Event>, listener as Listener<out Event>)
    }

    fun <E: Event> removeListener(
        mapper: EventMapper<E>,
        listener: Listener<E>
    ) {
        removeListener(mapper.type, listener)
    }

    fun <E: Event> removeListener(
        event: KClass<E>,
        listener: (E) -> Unit
    ) {
        val wrapper = listeners[event]?.firstOrNull { it.handler == listener }
        if (wrapper != null) {
            unsafeRemoveListener(event, wrapper)
        }
    }

    fun <E: Event> removeListener(
        mapper: EventMapper<E>,
        listener: (E) -> Unit
    ) {
        removeListener(mapper.type, listener)
    }

    fun onEvent(event: Event) {
        listeners[event::class]
            ?.filter { listener -> listenerFilters[listener]?.invoke(event) != false  }
            ?.forEach { listener ->
                listener(event)
                if (oneTimeListeners.contains(listener)) {
                    unsafeRemoveListener(event::class, listener)
                }
            }
    }

}