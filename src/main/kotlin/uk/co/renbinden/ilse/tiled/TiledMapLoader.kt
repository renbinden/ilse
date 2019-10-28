package uk.co.renbinden.ilse.tiled

import org.w3c.dom.Element
import org.w3c.dom.asList
import org.w3c.dom.parsing.DOMParser
import uk.co.renbinden.ilse.asset.TextAsset
import uk.co.renbinden.ilse.color.Color
import kotlin.browser.window


object TiledMapLoader {

    @ExperimentalUnsignedTypes
    @ExperimentalStdlibApi
    fun loadMap(text: TextAsset): TiledMap? {
        val parser = DOMParser()
        val xmlDoc = parser.parseFromString(text.text, "text/xml")
        return parseMap(xmlDoc.getElementsByTagName("map").asList().first())
    }

    @ExperimentalUnsignedTypes
    @ExperimentalStdlibApi
    private fun parseMap(element: Element): TiledMap {
        return TiledMap(
            element.getAttribute("version") ?: throw TiledMapParseException("Failed to parse map: version not present"),
            element.getAttribute("tiledversion"),
            try {
                MapOrientation.valueOf(element.getAttribute("orientation")?.toUpperCase() ?: "")
            } catch (exception: IllegalArgumentException) {
                null
            } catch (exception: IllegalStateException) {
                null
            },
            try {
                RenderOrder.valueOf(element.getAttribute("renderorder")?.toUpperCase()?.replace('-', '_') ?: "")
            } catch (exception: IllegalArgumentException) {
                null
            } catch (exception: IllegalStateException) {
                null
            },
            element.getAttribute("width")?.toInt() ?: throw TiledMapParseException("Failed to parse map: width not present"),
            element.getAttribute("height")?.toInt() ?: throw TiledMapParseException("Failed to parse map: height not present"),
            element.getAttribute("tilewidth")?.toInt() ?: throw TiledMapParseException("Failed to parse map: tilewidth not present"),
            element.getAttribute("tileheight")?.toInt() ?: throw TiledMapParseException("Failed to parse map: tileheight not present"),
            element.getAttribute("hexsidelength")?.toInt(),
            try {
                StaggerAxis.valueOf(element.getAttribute("staggeraxis")?.toUpperCase() ?: "")
            } catch (exception: IllegalArgumentException) {
                null
            } catch (exception: IllegalStateException) {
                null
            },
            try {
                StaggerIndex.valueOf(element.getAttribute("staggerindex")?.toUpperCase() ?: "")
            } catch (exception: IllegalArgumentException) {
                null
            } catch (exception: IllegalStateException) {
                null
            },
            Color.fromARGBHexCode(element.getAttribute("backgroundcolor") ?: "#FF000000"),
            element.getAttribute("nextlayerid")?.toInt(),
            element.getAttribute("nextobjectid")?.toInt(),
            Properties(element.getElementsByTagName("properties").asList().map(::parseProperties)),
            element.getElementsByTagName("tileset").asList().map(::parseTileset),
            element.getElementsByTagName("layer").asList().map(::parseLayer),
            element.getElementsByTagName("objectgroup").asList().map(::parseObjectGroup),
            element.getElementsByTagName("imagelayer").asList().map(::parseImageLayer),
            element.getElementsByTagName("group").asList().map(::parseGroup)
        )
    }

    private fun parseProperties(element: Element): Properties {
        return Properties(element.getElementsByTagName("property").asList().map(::parseProperty))
    }

    private fun parseProperty(element: Element): Property<Any> {
        val name = element.getAttribute("name") ?: ""
        val type = Type.valueOf(element.getAttribute("type") ?: "string")
        val value = type.coerce(element.getAttribute("value") ?: "")
        return Property(name, type, value)
    }

    @ExperimentalUnsignedTypes
    @ExperimentalStdlibApi
    private fun parseTileset(element: Element): TileSet {
        val terrainTypes = element.getElementsByTagName("terraintypes").asList().map(::parseTerrainTypes).firstOrNull()
        return TileSet(
            element.getAttribute("firstgid")?.toInt() ?: throw TiledMapParseException("Failed to parse tileset: firstgid not present"),
            element.getAttribute("source"),
            element.getAttribute("name") ?: throw TiledMapParseException("Failed to parse tileset: name not present"),
            element.getAttribute("tilewidth")?.toInt() ?: throw TiledMapParseException("Failed to parse tileset: tilewidth not present"),
            element.getAttribute("tileheight")?.toInt() ?: throw TiledMapParseException("Failed to parse tileset: tileheight not present"),
            element.getAttribute("spacing")?.toInt(),
            element.getAttribute("margin")?.toInt(),
            element.getAttribute("tilecount")?.toInt(),
            element.getAttribute("columns")?.toInt(),
            element.getElementsByTagName("tileoffset").asList().map(::parseTileOffset).firstOrNull(),
            element.getElementsByTagName("grid").asList().map(::parseGrid).firstOrNull(),
            Properties(element.getElementsByTagName("properties").asList().map(::parseProperties)),
            element.getElementsByTagName("image").asList().map(::parseImage).firstOrNull(),
            terrainTypes,
            element.getElementsByTagName("tile").asList().map { parseTile(it, terrainTypes) },
            element.getElementsByTagName("wangsets").asList().map(::parseWangSets)
        )
    }

    private fun parseTileOffset(element: Element): TileOffset {
        return TileOffset(
            element.getAttribute("x")?.toInt() ?: throw TiledMapParseException("Failed to parse tile offset: x not present"),
            element.getAttribute("y")?.toInt() ?: throw TiledMapParseException("Failed to parse tile offset: y not present")
        )
    }

    private fun parseGrid(element: Element): Grid {
        return Grid(
            GridOrientation.valueOf(element.getAttribute("orientation")?.toUpperCase() ?: ""),
            element.getAttribute("width")?.toInt() ?: throw TiledMapParseException("Failed to parse grid: width not present"),
            element.getAttribute("height")?.toInt() ?: throw TiledMapParseException("Failed to parse grid: height not present")
        )
    }

    @ExperimentalStdlibApi
    private fun parseImage(element: Element): Image {
        return Image(
            element.getAttribute("format"),
            element.getAttribute("source") ?: throw TiledMapParseException("Failed to parse image: source not present"),
            Color.fromARGBHexCode(element.getAttribute("trans") ?: ""),
            element.getAttribute("width")?.toInt(),
            element.getAttribute("height")?.toInt(),
            element.getElementsByTagName("data").asList().map(::parseData).firstOrNull()
        )
    }

    private fun parseTerrainTypes(element: Element): TerrainTypes {
        return TerrainTypes(
            element.getElementsByTagName("terrain").asList().map(::parseTerrain)
        )
    }

    private fun parseTerrain(element: Element): Terrain {
        return Terrain(
            element.getAttribute("name") ?: throw TiledMapParseException("Failed to parse terrain: name not present"),
            TileInstance(element.getAttribute("tile")?.toInt() ?: throw TiledMapParseException("Failed to parse terrain: tile not present")) ,
            Properties(element.getElementsByTagName("properties").asList().map(::parseProperties))
        )
    }

    @ExperimentalStdlibApi
    private fun parseTile(element: Element, terrainTypes: TerrainTypes?): Tile {
        return Tile(
            element.getAttribute("id")?.toInt() ?: throw TiledMapParseException("Failed to parse tile: id not present"),
            element.getAttribute("type"),
            element.getAttribute("terrain")
                ?.split(",")
                ?.mapNotNull { id -> id.toInt().let { terrainTypes?.terrains?.get(it) } },
            element.getAttribute("probability")?.toInt(),
            Properties(element.getElementsByTagName("properties").asList().map(::parseProperties)),
            element.getElementsByTagName("image").asList().map(::parseImage).firstOrNull(),
            element.getElementsByTagName("objectgroup").asList().map(::parseObjectGroup),
            element.getElementsByTagName("animation").asList().map(::parseAnimation).firstOrNull()
        )
    }

    private fun parseAnimation(element: Element): Animation {
        return Animation(
            element.getElementsByTagName("frame").asList().map(::parseFrame)
        )
    }

    private fun parseFrame(element: Element): Frame {
        return Frame(
            TileInstance(element.getAttribute("tileid")?.toInt() ?: throw TiledMapParseException("Failed to parse frame: tileid not present")),
            element.getAttribute("duration")?.toLong() ?: throw TiledMapParseException("Failed to parse frame: duration not present")
        )
    }

    @ExperimentalUnsignedTypes
    private fun parseWangSets(element: Element): WangSets {
        return WangSets(
            element.getElementsByTagName("wangset").asList().map(::parseWangSet)
        )
    }

    @ExperimentalUnsignedTypes
    private fun parseWangSet(element: Element): WangSet {
        return WangSet(
            element.getAttribute("name") ?: throw TiledMapParseException("Failed to parse wang set: name not present"),
            TileInstance(element.getAttribute("tile")?.toInt()),
            element.getElementsByTagName("wangcornercolor").asList().map(::parseWangCornerColor),
            element.getElementsByTagName("wangedgecolor").asList().map(::parseWangEdgeColor),
            element.getElementsByTagName("wangtile").asList().map(::parseWangTile)
        )
    }

    private fun parseWangCornerColor(element: Element): WangCornerColor {
        return WangCornerColor(
            element.getAttribute("name") ?: throw TiledMapParseException("Failed to parse wang corner color: name not present"),
            Color.fromARGBHexCode(element.getAttribute("color") ?: throw TiledMapParseException("Failed to parse wang corner color: color not present")) ?: throw TiledMapParseException("Failed to parse wang corner color: failed to parse color"),
            TileInstance(element.getAttribute("tile")?.toInt()),
            element.getAttribute("probability")?.toInt()
        )
    }

    private fun parseWangEdgeColor(element: Element): WangEdgeColor {
        return WangEdgeColor(
            element.getAttribute("name") ?: throw TiledMapParseException("Failed to parse wang edge color: name not present"),
            Color.fromARGBHexCode(element.getAttribute("color") ?: throw TiledMapParseException("Failed to parse wang edge color: color not present")) ?: throw TiledMapParseException("Failed to parse "),
            TileInstance(element.getAttribute("tile")?.toInt()),
            element.getAttribute("probability")?.toInt() ?: throw TiledMapParseException("Failed to parse wang edge color")
        )
    }

    @ExperimentalUnsignedTypes
    private fun parseWangTile(element: Element): WangTile {
        return WangTile(
            TileInstance(element.getAttribute("tileid")?.toInt() ?: throw TiledMapParseException("Failed to parse wang tile: tileid not present")),
            element.getAttribute("wangid")?.toUInt() ?: throw TiledMapParseException("Failed to parse wang tile: wangid not present")
        )
    }

    @ExperimentalStdlibApi
    private fun parseLayer(element: Element): Layer {
        return parseLayer(
            element,
            defaultOpacity = 1.0,
            defaultVisibility = true,
            defaultOffsetX = 0,
            defaultOffsetY = 0
        )
    }

    @ExperimentalStdlibApi
    private fun parseLayer(
        element: Element,
        defaultOpacity: Double = 1.0,
        defaultVisibility: Boolean = true,
        defaultOffsetX: Int = 0,
        defaultOffsetY: Int = 0
    ): Layer {
        return Layer(
            element.getAttribute("id")?.toInt(),
            element.getAttribute("name") ?: throw TiledMapParseException("Failed to parse layer: name not present"),
            element.getAttribute("width")?.toInt() ?: throw TiledMapParseException("Failed to parse layer: width not present"),
            element.getAttribute("height")?.toInt() ?: throw TiledMapParseException("Failed to parse layer: height not present"),
            element.getAttribute("opacity")?.toDouble() ?: defaultOpacity,
            element.getAttribute("visible")?.toInt() ?: (if (defaultVisibility) 1 else 0) == 1,
            element.getAttribute("offsetx")?.toInt() ?: defaultOffsetX,
            element.getAttribute("offsety")?.toInt() ?: defaultOffsetY,
            Properties(element.getElementsByTagName("properties").asList().map(::parseProperties)),
            element.getElementsByTagName("data").asList().map(::parseData).first()
        )
    }

    @ExperimentalStdlibApi
    private fun parseData(element: Element): Data {
        var tiles: List<TileInstance?> = emptyList()
        var chunks: List<Chunk> = emptyList()
        val encoding = element.getAttribute("encoding")
        val compression = element.getAttribute("compression")
        if (encoding == null && compression == null) {
            tiles = element.getElementsByTagName("tile").asList().map(::parseTileInstance)
            chunks = element.getElementsByTagName("chunk").asList().map { parseChunk(it, encoding, compression) }
        } else if (encoding == "csv") {
            tiles = element.textContent
                ?.split(",")
                ?.map { idString ->
                    val id = idString.trim().toInt()
                    if (id == 0) null else TileInstance(id)
                }
                ?: emptyList()
        } else if (encoding == "base64") {
            var bytes = window.atob(element.nodeValue ?: "").encodeToByteArray()
            when (compression) {
                "gzip" -> TODO("Implement GZip")
                "zlib" -> TODO("Implement ZLib")
                else -> TODO("Implement no compression base64")
            }
        }
        return Data(encoding, compression, tiles, chunks)
    }

    private fun parseTileInstance(element: Element): TileInstance {
        return TileInstance(element.getAttribute("gid")?.toInt())
    }

    private fun parseChunk(element: Element, encoding: String?, compression: String?): Chunk {
        var tiles: List<TileInstance?> = emptyList()
        if (encoding == null && compression == null) {
            tiles = element.getElementsByTagName("tile").asList().map(::parseTileInstance)
        } else if (encoding == "csv") {
            tiles = element.textContent
                ?.split(",")
                ?.map { idString ->
                    val id = idString.trim().toInt()
                    if (id == 0) null else TileInstance(id)
                }
                ?: emptyList()
        }
        return Chunk(
            element.getAttribute("x")?.toInt() ?: throw TiledMapParseException("Failed to parse chunk: x not present"),
            element.getAttribute("y")?.toInt() ?: throw TiledMapParseException("Failed to parse chunk: y not present"),
            element.getAttribute("width")?.toInt() ?: throw TiledMapParseException("Failed to parse chunk: width not present"),
            element.getAttribute("height")?.toInt() ?: throw TiledMapParseException("Failed to parse chunk: height not present"),
            tiles
        )
    }

    @ExperimentalStdlibApi
    private fun parseObjectGroup(element: Element): ObjectGroup {
        return parseObjectGroup(
            element,
            defaultOpacity = 1.0,
            defaultVisibility = true,
            defaultOffsetX = 0,
            defaultOffsetY = 0
        )
    }

    @ExperimentalStdlibApi
    private fun parseObjectGroup(
        element: Element,
        defaultOpacity: Double = 1.0,
        defaultVisibility: Boolean = true,
        defaultOffsetX: Int = 0,
        defaultOffsetY: Int = 0
    ): ObjectGroup {
        return ObjectGroup(
            element.getAttribute("id")?.toInt(),
            element.getAttribute("name") ?: throw TiledMapParseException("Failed to parse object group: name not present"),
            Color.fromARGBHexCode(element.getAttribute("color") ?: ""),
            element.getAttribute("opacity")?.toDouble() ?: defaultOpacity,
            element.getAttribute("visible")?.toInt() ?: (if (defaultVisibility) 1 else 0) == 1,
            element.getAttribute("offsetx")?.toInt() ?: defaultOffsetX,
            element.getAttribute("offsety")?.toInt() ?: defaultOffsetY,
            DrawOrder.fromString(element.getAttribute("draworder")?.toUpperCase() ?: ""),
            Properties(element.getElementsByTagName("properties").asList().map(::parseProperties)),
            element.getElementsByTagName("object").asList().map(::parseObject)
        )
    }

    @ExperimentalStdlibApi
    private fun parseObject(element: Element): Object {
        return Object(
            element.getAttribute("id")?.toInt() ?: throw TiledMapParseException("Failed to parse object: id not present"),
            element.getAttribute("name") ?: "",
            element.getAttribute("type") ?: "",
            element.getAttribute("x")?.toDouble() ?: throw TiledMapParseException("Failed to parse object: x not present"),
            element.getAttribute("y")?.toDouble() ?: throw TiledMapParseException("Failed to parse object: y not present"),
            element.getAttribute("width")?.toDouble(),
            element.getAttribute("height")?.toDouble(),
            element.getAttribute("rotation")?.toDouble(),
            element.getAttribute("gid")?.toInt(),
            element.getAttribute("visible")?.toInt() ?: 1 == 1,
            element.getAttribute("template"),
            Properties(element.getElementsByTagName("properties").asList().map(::parseProperties)),
            element.getElementsByTagName("ellipse").asList().map(::parseEllipse).firstOrNull(),
            element.getElementsByTagName("point").asList().map(::parsePoint).firstOrNull(),
            element.getElementsByTagName("polygon").asList().map(::parsePolygon).firstOrNull(),
            element.getElementsByTagName("polyline").asList().map(::parsePolyline).firstOrNull(),
            element.getElementsByTagName("text").asList().map(::parseText).firstOrNull(),
            element.getElementsByTagName("image").asList().map(::parseImage).firstOrNull()
        )
    }

    private fun parseEllipse(element: Element): Ellipse {
        return Ellipse()
    }

    private fun parsePoint(element: Element): Point {
        return Point()
    }

    private fun parsePolygon(element: Element): Polygon {
        return Polygon(
            element.getAttribute("points")?.split(" ")?.map { coordsString ->
                val coordsList = coordsString.split(",").map(String::toDouble)
                Pair(coordsList[0], coordsList[1])
            } ?: throw TiledMapParseException("Failed to parse polygon: points not present")
        )
    }

    private fun parsePolyline(element: Element): Polyline {
        return Polyline(
            element.getAttribute("points")?.split(" ")?.map { coordsString ->
                val coordsList = coordsString.split(",").map(String::toDouble)
                Pair(coordsList[0], coordsList[1])
            } ?: throw TiledMapParseException("Failed to parse polyline: points not present")
        )
    }

    private fun parseText(element: Element): Text {
        return Text(
            element.getAttribute("fontfamily"),
            element.getAttribute("pixelsize")?.toInt(),
            element.getAttribute("wrap")?.toInt() ?: 0 == 1,
            Color.fromARGBHexCode(element.getAttribute("color") ?: "#000000"),
            element.getAttribute("bold")?.toInt() == 1,
            element.getAttribute("italic")?.toInt() == 1,
            element.getAttribute("underline")?.toInt() == 1,
            element.getAttribute("strikeout")?.toInt() == 1,
            element.getAttribute("kerning")?.toInt() == 1,
            HAlign.valueOf(element.getAttribute("halign")?.toUpperCase() ?: "LEFT"),
            VAlign.valueOf(element.getAttribute("valign")?.toUpperCase() ?: "TOP")
        )
    }

    @ExperimentalStdlibApi
    private fun parseImageLayer(element: Element): ImageLayer {
        return parseImageLayer(
            element,
            defaultOpacity = 1.0,
            defaultVisibility = true,
            defaultOffsetX = 0,
            defaultOffsetY = 0
        )
    }

    @ExperimentalStdlibApi
    private fun parseImageLayer(
        element: Element,
        defaultOpacity: Double = 1.0,
        defaultVisibility: Boolean = true,
        defaultOffsetX: Int = 0,
        defaultOffsetY: Int = 0
    ): ImageLayer {
        return ImageLayer(
            element.getAttribute("id")?.toInt(),
            element.getAttribute("name") ?: throw IllegalArgumentException("Failed to parse image layer: name not present"),
            element.getAttribute("offsetx")?.toInt() ?: defaultOffsetX,
            element.getAttribute("offsety")?.toInt() ?: defaultOffsetY,
            element.getAttribute("opacity")?.toDouble() ?: defaultOpacity,
            element.getAttribute("visible")?.toInt() ?: (if (defaultVisibility) 1 else 0) == 1,
            Properties(element.getElementsByTagName("properties").asList().map(::parseProperties)),
            element.getElementsByTagName("image").asList().map(::parseImage).first()
        )
    }

    @ExperimentalStdlibApi
    private fun parseGroup(element: Element): Group {
        return parseGroup(
            element,
            defaultOpacity = 1.0,
            defaultVisibility = true,
            defaultOffsetX = 0,
            defaultOffsetY = 0
        )
    }

    @ExperimentalStdlibApi
    private fun parseGroup(
        element: Element,
        defaultOpacity: Double = 1.0,
        defaultVisibility: Boolean = true,
        defaultOffsetX: Int = 0,
        defaultOffsetY: Int = 0
    ): Group {
        val offsetX = element.getAttribute("offsetx")?.toInt() ?: defaultOffsetX
        val offsetY = element.getAttribute("offsety")?.toInt() ?: defaultOffsetY
        val opacity = element.getAttribute("opacity")?.toDouble() ?: defaultOpacity
        val visibility = element.getAttribute("visible")?.toInt() ?: (if (defaultVisibility) 1 else 0) == 1
        return Group(
            element.getAttribute("id")?.toInt(),
            element.getAttribute("name") ?: throw TiledMapParseException("Failed to parse group: name not present"),
            offsetX,
            offsetY,
            opacity,
            visibility,
            Properties(element.getElementsByTagName("properties").asList().map(::parseProperties)),
            element.getElementsByTagName("layer").asList().map { parseLayer(it, opacity, visibility, offsetX, offsetY) },
            element.getElementsByTagName("objectgroup").asList().map { parseObjectGroup(it, opacity, visibility, offsetX, offsetY) },
            element.getElementsByTagName("imagelayer").asList().map { parseImageLayer(it, opacity, visibility, offsetX, offsetY) },
            element.getElementsByTagName("group").asList().map { parseGroup(it, opacity, visibility, offsetX, offsetY) }
        )
    }

}