package uk.co.renbinden.ilse.tiled


class PolylineObject(
    id: Int?,
    name: String,
    type: String,
    x: Double,
    y: Double,
    width: Int?,
    height: Int?,
    rotation: Int?,
    gid: Int?,
    visible: Boolean?,
    template: String?,
    properties: Properties,
    val polyline: Polyline
) : Object(
    id,
    name,
    type,
    x,
    y,
    width,
    height,
    rotation,
    gid,
    visible,
    template,
    properties
)