package uk.co.renbinden.ilse.input.event

import uk.co.renbinden.ilse.event.EventMapper


class KeyUpEvent(keyboardEvent: org.w3c.dom.events.KeyboardEvent) : KeyboardEvent(keyboardEvent) {
    companion object : EventMapper<KeyUpEvent>(KeyUpEvent::class)
}