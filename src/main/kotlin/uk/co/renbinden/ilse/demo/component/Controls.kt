package uk.co.renbinden.ilse.demo.component

import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.component.ComponentMapper


class Controls(
    var leftKey: Int,
    var rightKey: Int,
    var upKey: Int,
    var downKey: Int
) : Component() {

    companion object: ComponentMapper<Controls>(Controls::class)

}