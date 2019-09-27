package uk.co.renbinden.ilse.demo.level

import uk.co.renbinden.ilse.asset.TextAsset

@ExperimentalStdlibApi
fun loadLevel(map: TextAsset, gridSize: Double = 32.0, loadEntity: (Char, Double, Double) -> Unit) {
    map.addLoadListener {
        var x = 0.0
        var y = 0.0
        map.text.split("\n").forEach { row ->
            row.toCharArray().forEach { obj ->
                loadEntity(obj, x, y)
                x += gridSize
            }
            x = 0.0
            y += gridSize
        }
    }
}