package uk.co.renbinden.ilse.tiled

import uk.co.renbinden.ilse.color.Color
import uk.co.renbinden.ilse.tiled.RenderOrder.RIGHT_DOWN


class TiledMap(
    val version: String,
    val tiledVersion: String?,
    val orientation: MapOrientation?,
    renderOrder: RenderOrder?,
    val width: Int,
    val height: Int,
    val tileWidth: Int,
    val tileHeight: Int,
    val hexSideLength: Int?,
    val staggerAxis: StaggerAxis?,
    val staggerIndex: StaggerIndex?,
    val backgroundColor: Color?,
    val nextLayerId: Int?,
    val nextObjectId: Int?,
    val properties: Properties,
    val tileSets: List<TileSet>,
    val layers: List<Layer>,
    val objectGroups: List<ObjectGroup>,
    val imageLayers: List<ImageLayer>,
    val groups: List<Group>
) {

    val renderOrder = renderOrder ?: RIGHT_DOWN

    fun getTileSet(tileInstance: TileInstance): TileSet {
        return tileSets.last { tileSet -> tileSet.firstGid <= tileInstance.gid }
    }

}