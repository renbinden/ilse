package uk.co.renbinden.ilse.ecs.event

import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.entity.Entity
import uk.co.renbinden.ilse.event.Event
import uk.co.renbinden.ilse.event.EventMapper

class ComponentRemovedFromEntityEvent(val component: Component, val entity: Entity) : Event {
    companion object : EventMapper<ComponentRemovedFromEntityEvent>(ComponentRemovedFromEntityEvent::class)
}
