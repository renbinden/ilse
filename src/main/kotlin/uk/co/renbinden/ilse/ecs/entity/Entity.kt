package uk.co.renbinden.ilse.ecs.entity

import uk.co.renbinden.ilse.ecs.ECSMarker
import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.component.ComponentMapper
import uk.co.renbinden.ilse.ecs.event.ComponentAddedToEntityEvent
import uk.co.renbinden.ilse.ecs.event.ComponentRemovedFromEntityEvent
import uk.co.renbinden.ilse.ecs.exception.EntityComponentMissingException
import uk.co.renbinden.ilse.event.Events
import kotlin.reflect.KClass

@ECSMarker
class Entity {
    val components = mutableMapOf<KClass<out Component>, Component>()

    fun add(component: Component) {
        components[component::class] = component
        Events.onEvent(ComponentAddedToEntityEvent(component, this))
    }

    fun remove(component: Component) {
        components.remove(component::class)
        Events.onEvent(ComponentRemovedFromEntityEvent(component, this))
    }

    fun <T: Component> has(type: KClass<T>): Boolean {
        return components.containsKey(type)
    }

    fun <T: Component> has(mapper: ComponentMapper<T>): Boolean {
        return mapper.has(this)
    }

    operator fun <T: Component> get(type: KClass<T>): T {
        if (!has(type)) throw EntityComponentMissingException(type)
        return components[type] as T
    }

    operator fun <T: Component> get(mapper: ComponentMapper<T>): T {
        return mapper[this]
    }
}

fun entity(init: Entity.() -> Unit): Entity {
    val entity = Entity()
    entity.init()
    return entity
}