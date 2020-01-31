package uk.co.renbinden.ilse.ecs.event

import uk.co.renbinden.ilse.ecs.Engine
import uk.co.renbinden.ilse.ecs.entity.Entity
import uk.co.renbinden.ilse.event.Event
import uk.co.renbinden.ilse.event.EventMapper


class EntityAddedToEngineEvent(val entity: Entity, val engine: Engine) : Event {
    companion object : EventMapper<EntityAddedToEngineEvent>(EntityAddedToEngineEvent::class)
}