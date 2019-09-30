package uk.co.renbinden.ilse.demo.component

import uk.co.renbinden.ilse.collision.RectangleCollider
import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.component.ComponentMapper


class Collider(val collider: RectangleCollider) : Component {

    companion object: ComponentMapper<Collider>(Collider::class)

}