package uk.co.renbinden.ilse.demo.component

import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.component.ComponentMapper


class Dimensions(
    var width: Double,
    var height: Double
) : Component() {

    companion object: ComponentMapper<Dimensions>(Dimensions::class)

}