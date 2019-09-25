package uk.co.renbinden.ilse.demo.system

import uk.co.renbinden.ilse.demo.component.Acceleration
import uk.co.renbinden.ilse.demo.component.Velocity
import uk.co.renbinden.ilse.ecs.entity.Entity
import uk.co.renbinden.ilse.ecs.system.IteratingSystem


class AccelerationSystem : IteratingSystem({
    it.has(Acceleration)
            && it.has(Velocity)
}, priority = 2) {

    override fun processEntity(entity: Entity, dt: Double) {
        val acceleration = entity[Acceleration]
        val velocity = entity[Velocity]
        velocity.dx += acceleration.ddx
        velocity.dy += acceleration.ddy
    }

}