package uk.co.renbinden.ilse.ecs

import uk.co.renbinden.ilse.ecs.entity.Entity
import uk.co.renbinden.ilse.ecs.system.System
import kotlin.math.abs

@ECSMarker
class Engine {
    val systems = mutableListOf<System>()
    val entities = mutableListOf<Entity>()

    fun add(system: System) {
        system.engine = this
        systems.add(abs(systems.binarySearchBy(system.priority, selector = System::priority)) - 1, system)
    }

    fun remove(system: System) {
        systems.remove(system)
    }

    fun add(entity: Entity) {
        entities.add(entity)
        systems.forEach { system -> system.onEntityAdded(entity) }
    }

    fun remove(entity: Entity) {
        entities.remove(entity)
        systems.forEach { system -> system.onEntityRemoved(entity) }
    }

    fun onTick(dt: Double) {
        systems.forEach { system -> system.onTick(dt) }
    }
}

fun engine(init: Engine.() -> Unit): Engine {
    val engine = Engine()
    engine.init()
    return engine
}