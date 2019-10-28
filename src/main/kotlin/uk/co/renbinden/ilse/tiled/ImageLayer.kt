package uk.co.renbinden.ilse.tiled


class ImageLayer(
    val id: Int?,
    val name: String,
    offsetX: Int? = null,
    offsetY: Int? = null,
    opacity: Double? = null,
    visible: Boolean? = null,
    val properties: Properties,
    val image: Image
) {
    
    val offsetX = offsetX ?: 0
    val offsetY = offsetY ?: 0
    val opacity = opacity ?: 1
    val visible = visible ?: true
    
}