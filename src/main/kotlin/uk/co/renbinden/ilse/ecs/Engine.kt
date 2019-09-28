package uk.co.renbinden.ilse.ecs

import uk.co.renbinden.ilse.ecs.entity.Entity
import uk.co.renbinden.ilse.ecs.event.EntityAddedToEngineEvent
import uk.co.renbinden.ilse.ecs.event.EntityRemovedFromEngineEvent
import uk.co.renbinden.ilse.ecs.event.SystemAddedToEngineEvent
import uk.co.renbinden.ilse.ecs.event.SystemRemovedFromEngineEvent
import uk.co.renbinden.ilse.ecs.system.System
import uk.co.renbinden.ilse.event.Events
import kotlin.math.abs

@ECSMarker
class Engine {
    val systems = mutableListOf<System>()
    val entities = mutableListOf<Entity>()

    fun add(system: System) {
        system.engine = this
        systems.add(abs(systems.binarySearchBy(system.priority, selector = System::priority)) - 1, system)
        Events.onEvent(SystemAddedToEngineEvent(system, this))
    }

    fun remove(system: System) {
        systems.remove(system)
        Events.onEvent(SystemRemovedFromEngineEvent(system, this))
    }

    fun add(entity: Entity) {
        entities.add(entity)
        systems.forEach { system -> system.onEntityAdded(entity) }
        Events.onEvent(EntityAddedToEngineEvent(entity, this))
    }

    fun remove(entity: Entity) {
        entities.remove(entity)
        systems.forEach { system -> system.onEntityRemoved(entity) }
        Events.onEvent(EntityRemovedFromEngineEvent(entity, this))
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