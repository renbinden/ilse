package uk.co.renbinden.ilse.ecs.event

import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.entity.Entity
import uk.co.renbinden.ilse.event.Event

class ComponentRemovedFromEntityEvent(val component: Component, val entity: Entity) : Event
