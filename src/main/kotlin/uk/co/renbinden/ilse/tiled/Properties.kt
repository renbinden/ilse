package uk.co.renbinden.ilse.tiled


class Properties(
    val properties: Array<Property<Any>>
) {

    constructor(vararg properties: Properties): this(properties.toList().flatMap { it.properties.toList() }.toTypedArray())
    constructor(properties: Iterable<Properties>): this(properties.toList().flatMap { it.properties.toList() }.toTypedArray())
    constructor(vararg properties: Property<Any>): this(properties.toList().toTypedArray())

    operator fun get(name: String): Any? {
        return properties.firstOrNull { it.name == name }?.value
    }

}