package uk.co.renbinden.ilse.tiled


class Chunk(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val tiles: List<TileInstance?>
)