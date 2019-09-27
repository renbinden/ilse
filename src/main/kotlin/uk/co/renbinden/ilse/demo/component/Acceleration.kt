package uk.co.renbinden.ilse.demo.component

import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.component.ComponentMapper
import kotlin.math.max
import kotlin.math.min

class Acceleration(
    ddx: Double,
    ddy: Double,
    var ddxMax: Double? = null,
    var ddyMax: Double? = null
): Component {
    var ddx: Double = ddx
        set(value) {
            val ddxMax = ddxMax
            field = if (ddxMax != null) {
                max(-ddxMax, min(value, ddxMax))
            } else {
                value
            }
        }

    var ddy: Double = ddy
        set(value) {
            val ddyMax = ddyMax
            field = if (ddyMax != null) {
                max(-ddyMax, min(value, ddyMax))
            } else {
                value
            }
        }

    companion object: ComponentMapper<Acceleration>(Acceleration::class)
}