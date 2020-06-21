package uk.co.renbinden.ilse.tiled


class TileInstance(gid: Int?) {
    val gid = gid ?: 0
    fun get(map: TiledMap): Tile? {
        val tileSet = map.getTileSet(this)
        return tileSet.tiles.firstOrNull { it.id + tileSet.firstGid == gid }
    }
}