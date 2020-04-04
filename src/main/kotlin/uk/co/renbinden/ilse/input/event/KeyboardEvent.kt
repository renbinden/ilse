package uk.co.renbinden.ilse.input.event

import uk.co.renbinden.ilse.event.Event


abstract class KeyboardEvent(private val keyboardEvent: org.w3c.dom.events.KeyboardEvent) : Event {

    val key = keyboardEvent.key
    val code = keyboardEvent.code
    val location = keyboardEvent.location
    val ctrlKey = keyboardEvent.ctrlKey
    val shiftKey = keyboardEvent.shiftKey
    val altKey = keyboardEvent.altKey
    val metaKey = keyboardEvent.metaKey
    val repeat = keyboardEvent.repeat
    val isComposing = keyboardEvent.isComposing
    val charCode = keyboardEvent.charCode
    val keyCode = keyboardEvent.keyCode
    val which = keyboardEvent.which

    fun preventDefault() {
        keyboardEvent.preventDefault()
    }

    fun stopPropagation() {
        keyboardEvent.stopPropagation()
    }

    fun stopImmediatePropagation() {
        keyboardEvent.stopImmediatePropagation()
    }

}