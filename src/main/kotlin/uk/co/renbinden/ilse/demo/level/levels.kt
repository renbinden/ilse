package uk.co.renbinden.ilse.demo.level

import uk.co.renbinden.ilse.asset.TextAsset
import uk.co.renbinden.ilse.asset.event.AssetLoadEvent
import uk.co.renbinden.ilse.event.Events

@ExperimentalStdlibApi
fun loadLevel(map: TextAsset, gridSize: Double = 32.0, loadEntity: (Char, Double, Double) -> Unit) {
    Events.addListener(AssetLoadEvent::class) { event ->
        if (event.asset == map) {
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
}