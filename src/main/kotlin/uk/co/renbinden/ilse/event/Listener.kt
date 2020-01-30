package uk.co.renbinden.ilse.event

open class Listener<E: Event>(val handler: (E) -> Unit, val priority: Int = 0) {

    operator fun invoke(event: E) {
        handler(event)
    }

}