package uk.co.renbinden.ilse.demo.system

import uk.co.renbinden.ilse.demo.component.Position
import uk.co.renbinden.ilse.demo.component.Velocity
import uk.co.renbinden.ilse.ecs.entity.Entity
import uk.co.renbinden.ilse.ecs.system.IteratingSystem


class VelocitySystem : IteratingSystem({
        it.has(Position)
                && it.has(Velocity)
}, priority = 3) {

    override fun processEntity(entity: Entity, dt: Double) {
        val velocity = entity[Velocity]
        val position = entity[Position]
        position.x += velocity.dx
        position.y += velocity.dy
    }

}