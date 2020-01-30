package uk.co.renbinden.ilse.input.event

import uk.co.renbinden.ilse.event.EventMapper


class KeyDownEvent(keyboardEvent: org.w3c.dom.events.KeyboardEvent) : KeyboardEvent(keyboardEvent) {
    companion object : EventMapper<KeyDownEvent>(KeyDownEvent::class)
}