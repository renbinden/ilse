package uk.co.renbinden.ilse.tiled


class Group(
    val id: Int?,
    val name: String,
    offsetX: Int? = null,
    offsetY: Int? = null,
    opacity: Double? = null,
    visible: Boolean? = null,
    val properties: Properties,
    val layers: List<Layer>,
    val objectGroups: List<ObjectGroup>,
    val imageLayers: List<ImageLayer>,
    val groups: List<Group>
) {
    
    val offsetX = offsetX ?: 0
    val offsetY = offsetY ?: 0
    val opacity = opacity ?: 1.0
    val visible = visible ?: true
    
}