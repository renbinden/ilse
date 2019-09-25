package uk.co.renbinden.ilse.app.screen

import uk.co.renbinden.ilse.ecs.Engine


abstract class Screen(val engine: Engine) {

    fun onTick(dt: Double) {
        engine.onTick(dt)
    }

    abstract fun onRender(dt: Double)

}