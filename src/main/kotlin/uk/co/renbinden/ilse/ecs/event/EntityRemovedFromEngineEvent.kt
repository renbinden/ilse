package uk.co.renbinden.ilse.ecs.event

import uk.co.renbinden.ilse.ecs.Engine
import uk.co.renbinden.ilse.ecs.entity.Entity
import uk.co.renbinden.ilse.event.Event
import uk.co.renbinden.ilse.event.EventMapper


class EntityRemovedFromEngineEvent(val entity: Entity, val engine: Engine) : Event {
    companion object : EventMapper<EntityRemovedFromEngineEvent>(EntityRemovedFromEngineEvent::class)
}