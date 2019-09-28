package uk.co.renbinden.ilse.ecs.event

import uk.co.renbinden.ilse.ecs.Engine
import uk.co.renbinden.ilse.ecs.system.System
import uk.co.renbinden.ilse.event.Event


class SystemAddedToEngineEvent(val system: System, val engine: Engine) : Event