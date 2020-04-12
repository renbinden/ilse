package uk.co.renbinden.ilse.net.event

import uk.co.renbinden.ilse.event.Event
import uk.co.renbinden.ilse.event.EventMapper
import uk.co.renbinden.ilse.net.Server

class ServerErrorEvent(val server: Server) : Event {
    companion object : EventMapper<ServerErrorEvent>(ServerErrorEvent::class)
}