package uk.co.renbinden.ilse.tiled


class TiledMapParseException(message: String?, cause: Throwable?): Exception(message, cause) {

    constructor(): this(null, null)
    constructor(message: String?): this(message, null)
    constructor(cause: Throwable?): this(undefined, cause)

}