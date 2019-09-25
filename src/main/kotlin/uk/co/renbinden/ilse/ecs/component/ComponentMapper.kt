package uk.co.renbinden.ilse.ecs.component

import uk.co.renbinden.ilse.ecs.entity.Entity
import kotlin.reflect.KClass


abstract class ComponentMapper<T: Component>(val type: KClass<T>) {

    fun has(entity: Entity): Boolean {
        return entity.has(type)
    }

    operator fun get(entity: Entity): T {
        return entity[type]
    }

}