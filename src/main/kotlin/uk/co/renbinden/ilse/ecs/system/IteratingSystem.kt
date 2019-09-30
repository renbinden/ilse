package uk.co.renbinden.ilse.ecs.system

import uk.co.renbinden.ilse.ecs.entity.Entity


abstract class IteratingSystem(
    val filter: (Entity) -> Boolean,
    priority: Int = 0,
    processing: Boolean = true
) : System(priority, processing) {

    override fun onTick(dt: Double) {
        engine.entities.filter(filter).forEach { entity -> processEntity(entity, dt) }
    }

    abstract fun processEntity(entity: Entity, dt: Double)

}