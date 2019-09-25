package uk.co.renbinden.ilse.demo.system

import uk.co.renbinden.ilse.demo.component.Controls
import uk.co.renbinden.ilse.demo.component.Velocity
import uk.co.renbinden.ilse.ecs.entity.Entity
import uk.co.renbinden.ilse.ecs.system.IteratingSystem
import uk.co.renbinden.ilse.input.Input


class ControlSystem : IteratingSystem({
    it.has(Controls)
            && it.has(Velocity)
}, priority = 1) {

    override fun processEntity(entity: Entity, dt: Double) {
        val controls = entity[Controls]
        val velocity = entity[Velocity]
        velocity.dx = 0.0
        velocity.dy = 0.0
        if (Input.isKeyPressed(controls.upKey)) {
            velocity.dy -= 4.0
        }
        if (Input.isKeyPressed(controls.downKey)) {
            velocity.dy += 4.0
        }
        if (Input.isKeyPressed(controls.leftKey)) {
            velocity.dx -= 4.0
        }
        if (Input.isKeyPressed(controls.rightKey)) {
            velocity.dx += 4.0
        }
    }

}