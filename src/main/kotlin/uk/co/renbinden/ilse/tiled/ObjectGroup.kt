package uk.co.renbinden.ilse.tiled

import uk.co.renbinden.ilse.color.Color
import uk.co.renbinden.ilse.tiled.DrawOrder.TOP_DOWN


class ObjectGroup(
    val id: Int?,
    val name: String,
    val color: Color?,
    opacity: Double? = null,
    visible: Boolean? = null,
    offsetX: Int? = null,
    offsetY: Int? = null,
    drawOrder: DrawOrder?,
    val properties: Properties,
    val objects: List<Object>
) {

    val opacity = opacity ?: 1.0
    val visible = visible ?: true
    val offsetX = offsetX ?: 0
    val offsetY = offsetY ?: 0
    val drawOrder = drawOrder ?: TOP_DOWN

}