package uk.co.renbinden.ilse.net

import org.khronos.webgl.ArrayBuffer
import org.w3c.dom.ARRAYBUFFER
import org.w3c.dom.BinaryType
import org.w3c.dom.WebSocket
import uk.co.renbinden.ilse.event.Events
import uk.co.renbinden.ilse.net.event.PacketReceivedEvent
import uk.co.renbinden.ilse.net.event.ServerConnectEvent
import uk.co.renbinden.ilse.net.event.ServerDisconnectEvent
import uk.co.renbinden.ilse.net.event.ServerErrorEvent

class Server(val address: String) {

    val socket = WebSocket(address)

    val isConnected
        get() = socket.readyState == WebSocket.OPEN

    init {
        socket.binaryType = BinaryType.ARRAYBUFFER
        socket.onopen = { Events.onEvent(ServerConnectEvent(this)) }
        socket.onclose = { Events.onEvent(ServerDisconnectEvent(this)) }
        socket.onerror = { Events.onEvent(ServerErrorEvent(this)) }
        socket.onmessage = { event ->
            Events.onEvent(PacketReceivedEvent(this, ClientboundPacket(event.data as ArrayBuffer)))
        }
    }

    fun send(packet: ServerboundPacket) {
        socket.send(packet.serialize())
    }

    fun disconnect() {
        socket.close()
    }

}