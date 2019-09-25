package uk.co.renbinden.ilse.demo.component

import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.component.ComponentMapper


class Position(
    x: Double,
    y: Double,
    var xMin: Double? = null,
    var xMax: Double? = null,
    var yMin: Double? = null,
    var yMax: Double? = null
) : Component() {

    var x: Double = x
        set(value) {
            val xMin = xMin
            val xMax = xMax
            field = when {
                xMin != null && value < xMin -> xMin
                xMax != null && value > xMax -> xMax
                else -> value
            }
        }

    var y: Double = y
        set(value) {
            val yMin = yMin
            val yMax = yMax
            field = when {
                yMin != null && value < yMin -> yMin
                yMax != null && value > yMax -> yMax
                else -> value
            }
        }

    companion object: ComponentMapper<Position>(Position::class)

}