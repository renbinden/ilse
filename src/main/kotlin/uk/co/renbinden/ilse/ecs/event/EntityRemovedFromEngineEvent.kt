package uk.co.renbinden.ilse.ecs.event

import uk.co.renbinden.ilse.ecs.Engine
import uk.co.renbinden.ilse.ecs.entity.Entity
import uk.co.renbinden.ilse.event.Event


class EntityRemovedFromEngineEvent(val entity: Entity, val engine: Engine) : Event