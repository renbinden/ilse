package uk.co.renbinden.ilse.net.event

import uk.co.renbinden.ilse.event.Event
import uk.co.renbinden.ilse.event.EventMapper
import uk.co.renbinden.ilse.net.Server

data class ServerDisconnectEvent(val server: Server) : Event {
    companion object : EventMapper<ServerDisconnectEvent>(ServerDisconnectEvent::class)
}