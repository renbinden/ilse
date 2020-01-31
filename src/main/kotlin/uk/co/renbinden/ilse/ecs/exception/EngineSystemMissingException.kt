package uk.co.renbinden.ilse.ecs.exception

import uk.co.renbinden.ilse.ecs.system.System
import kotlin.reflect.KClass


class EngineSystemMissingException(type: KClass<out System>) : Exception("Engine has system missing: ${type.simpleName}")