package uk.co.renbinden.ilse.tiled


class Tile(
    val id: Int,
    val type: String?,
    val terrain: List<Terrain>?,
    val probability: Int?,
    val properties: Properties,
    val image: Image?,
    val objectGroups: List<ObjectGroup>,
    val animation: Animation?
)