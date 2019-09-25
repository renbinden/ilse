package uk.co.renbinden.ilse.ecs.exception

import uk.co.renbinden.ilse.ecs.component.Component
import kotlin.reflect.KClass


class EntityComponentMissingException(type: KClass<out Component>) : Exception("Entity has component missing: ${type.simpleName}") {
}