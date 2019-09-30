package uk.co.renbinden.ilse.demo.component

import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.component.ComponentMapper


class Controls(
    var leftKey: Int,
    var rightKey: Int,
    var jumpKey: Int,
    var gamepadHorizontalAxes: Array<Int>,
    var gamepadJumpButton: Int
) : Component {

    companion object: ComponentMapper<Controls>(Controls::class)

}