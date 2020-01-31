package uk.co.renbinden.ilse.tiled


class Properties(
    val properties: List<Property<Any>>
) {

    constructor(vararg properties: Properties): this(properties.flatMap(Properties::properties))
    constructor(properties: Iterable<Properties>): this(properties.flatMap(Properties::properties))
    constructor(vararg properties: Property<Any>): this(properties.toList())

    operator fun get(name: String): Any? {
        return properties.firstOrNull { it.name == name }?.value
    }

}