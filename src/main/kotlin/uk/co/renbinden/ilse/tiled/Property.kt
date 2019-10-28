package uk.co.renbinden.ilse.tiled

import uk.co.renbinden.ilse.tiled.Type.Companion.STRING


class Property<out T>(val name: String, type: Type<out T>? = null, val value: T) {

    val type = type ?: STRING

}