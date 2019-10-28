package uk.co.renbinden.ilse.tiled


open class Object(
    val id: Int?,
    val name: String,
    val type: String,
    val x: Double,
    val y: Double,
    width: Double? = null,
    height: Double? = null,
    rotation: Double? = null,
    val gid: Int?,
    visible: Boolean? = null,
    val template: String?,
    val properties: Properties
) {

    val width = width ?: 0.0
    val height = height ?: 0.0
    val rotation = rotation ?: 0.0
    val visible = visible ?: true

    companion object {
        operator fun invoke(
            id: Int?,
            name: String,
            type: String,
            x: Double,
            y: Double,
            width: Double? = null,
            height: Double? = null,
            rotation: Double? = null,
            gid: Int?,
            visible: Boolean? = null,
            template: String?,
            properties: Properties,
            ellipse: Ellipse? = null,
            point: Point? = null,
            polygon: Polygon? = null,
            polyline: Polyline? = null,
            text: Text? = null,
            image: Image? = null
        ): Object {
            return when {
                ellipse != null -> EllipseObject(
                    id, name, type, x, y, width, height, rotation, gid, visible, template, properties, ellipse
                )
                point != null -> PointObject(
                    id, name, type, x, y, width, height, rotation, gid, visible, template, properties
                )
                polygon != null -> PolygonObject(
                    id, name, type, x, y, width, height, rotation, gid, visible, template, properties, polygon
                )
                polyline != null -> PolylineObject(
                    id, name, type, x, y, width, height, rotation, gid, visible, template, properties, polyline
                )
                text != null -> TextObject(
                    id, name, type, x, y, width, height, rotation, gid, visible, template, properties, text
                )
                image != null -> ImageObject(
                    id, name, type, x, y, width, height, rotation, gid, visible, template, properties, image
                )
                else -> Object(
                    id, name, type, x, y, width, height, rotation, gid, visible, template, properties
                )
            }
        }
    }

}