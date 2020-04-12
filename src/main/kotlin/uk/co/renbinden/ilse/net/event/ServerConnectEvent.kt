package uk.co.renbinden.ilse.net.event

import uk.co.renbinden.ilse.event.Event
import uk.co.renbinden.ilse.event.EventMapper
import uk.co.renbinden.ilse.net.Server

data class ServerConnectEvent(val server: Server) : Event {
    companion object : EventMapper<ServerConnectEvent>(ServerConnectEvent::class)
}