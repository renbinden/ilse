package uk.co.renbinden.ilse.ecs.event

import uk.co.renbinden.ilse.ecs.Engine
import uk.co.renbinden.ilse.ecs.system.System
import uk.co.renbinden.ilse.event.Event
import uk.co.renbinden.ilse.event.EventMapper


class SystemAddedToEngineEvent(val system: System, val engine: Engine) : Event {
    companion object : EventMapper<SystemAddedToEngineEvent>(SystemAddedToEngineEvent::class)
}