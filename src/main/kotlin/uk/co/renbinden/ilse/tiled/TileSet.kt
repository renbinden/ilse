package uk.co.renbinden.ilse.tiled


class TileSet(
    val firstGid: Int,
    val source: String?,
    val name: String,
    val tileWidth: Int,
    val tileHeight: Int,
    val spacing: Int?,
    val margin: Int?,
    val tileCount: Int?,
    val columns: Int?,
    val tileOffset: TileOffset?,
    val grid: Grid?,
    val properties: Properties,
    val image: Image?,
    val terrainTypes: TerrainTypes?,
    val tiles: List<Tile>,
    val wangSets: List<WangSets>
)