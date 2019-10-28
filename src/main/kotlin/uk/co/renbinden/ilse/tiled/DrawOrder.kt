package uk.co.renbinden.ilse.tiled


enum class DrawOrder(private val asString: String) {

    INDEX("index"),
    TOP_DOWN("topdown");

    override fun toString() = asString

    companion object {
        fun fromString(string: String) = values().firstOrNull { it.asString == string }
    }


}