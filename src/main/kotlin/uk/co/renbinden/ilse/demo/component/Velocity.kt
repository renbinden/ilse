package uk.co.renbinden.ilse.demo.component

import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.component.ComponentMapper
import kotlin.math.max
import kotlin.math.min


class Velocity(
    dx: Double,
    dy: Double,
    var dxMax: Double? = null,
    var dyMax: Double? = null
) : Component() {

    var dx: Double = dx
        set(value) {
            val dxMax = dxMax
            field = if (dxMax != null) {
                max(-dxMax, min(value, dxMax))
            } else {
                value
            }
        }

    var dy: Double = dy
        set(value) {
            val dyMax = dyMax
            field = if (dyMax != null) {
                max(-dyMax, min(value, dyMax))
            } else {
                value
            }
        }

    companion object: ComponentMapper<Velocity>(Velocity::class)

}