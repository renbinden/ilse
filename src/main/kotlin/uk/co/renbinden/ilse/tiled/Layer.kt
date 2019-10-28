package uk.co.renbinden.ilse.tiled


class Layer(
    val id: Int?,
    val name: String,
    val width: Int,
    val height: Int,
    opacity: Double? = null,
    visible: Boolean? = null,
    offsetX: Int? = null,
    offsetY: Int? = null,
    val properties: Properties,
    val data: Data
) {

    val opacity: Double = opacity ?: 1.0
    val visible: Boolean = visible ?: true
    val offsetX: Int = offsetX ?: 0
    val offsetY: Int = offsetY ?: 0

}