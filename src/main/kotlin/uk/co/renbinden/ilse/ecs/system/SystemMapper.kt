package uk.co.renbinden.ilse.ecs.system

import uk.co.renbinden.ilse.ecs.Engine
import kotlin.reflect.KClass

abstract class SystemMapper<T: System>(val type: KClass<T>) {

    fun has(engine: Engine): Boolean {
        return engine.has(type)
    }

    operator fun get(engine: Engine): T {
        return engine[type]
    }

}