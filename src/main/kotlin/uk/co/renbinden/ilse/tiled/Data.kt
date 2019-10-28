package uk.co.renbinden.ilse.tiled


class Data(
    val encoding: String?,
    val compression: String?,
    val tiles: List<TileInstance?>,
    val chunks: List<Chunk>
)