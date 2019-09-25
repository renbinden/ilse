package uk.co.renbinden.ilse.ecs.system

import uk.co.renbinden.ilse.ecs.Engine
import uk.co.renbinden.ilse.ecs.entity.Entity


abstract class System(var priority: Int = 0, var processing: Boolean = true) {

    lateinit var engine: Engine

    open fun onEntityAdded(entity: Entity) {}
    open fun onEntityRemoved(entity: Entity) {}
    open fun onTick(dt: Double) {}

}