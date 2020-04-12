package uk.co.renbinden.ilse.net

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.ArrayBufferView

interface ServerboundPacket : Packet {

    fun serialize(): ArrayBuffer

}

fun Int.toByteArray(): Array<Byte> {
    return arrayOf(
        ((this shr 24) and 0xff).toByte(),
        ((this shr 16) and 0xff).toByte(),
        ((this shr 8) and 0xff).toByte(),
        (this and 0xff).toByte()
    )
}