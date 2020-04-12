package uk.co.renbinden.ilse.net.event

import uk.co.renbinden.ilse.event.Event
import uk.co.renbinden.ilse.event.EventMapper
import uk.co.renbinden.ilse.net.ClientboundPacket
import uk.co.renbinden.ilse.net.Server

data class PacketReceivedEvent(val server: Server, val packet: ClientboundPacket) : Event {
    companion object : EventMapper<PacketReceivedEvent>(PacketReceivedEvent::class)
}