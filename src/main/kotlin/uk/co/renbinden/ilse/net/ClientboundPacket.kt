package uk.co.renbinden.ilse.net

import org.khronos.webgl.ArrayBuffer

interface ClientboundPacket : Packet {

    interface Deserializer {
        fun deserialize(data: ArrayBuffer): ClientboundPacket
    }

    companion object {

        lateinit var deserializer: Deserializer

        operator fun invoke(data: ArrayBuffer): ClientboundPacket {
            return deserializer.deserialize(data)
        }

    }

}