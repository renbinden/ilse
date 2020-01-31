package uk.co.renbinden.ilse.input.event

import uk.co.renbinden.ilse.event.EventMapper


class MouseUpEvent(mouseEvent: org.w3c.dom.events.MouseEvent) : MouseEvent(mouseEvent) {
    companion object : EventMapper<MouseUpEvent>(MouseUpEvent::class)
}