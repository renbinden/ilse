package uk.co.renbinden.ilse.app

import uk.co.renbinden.ilse.app.screen.Screen
import kotlin.browser.window
import kotlin.math.min


class App(var screen: Screen? = null) {

    var now = window.performance.now()
    var dt = 0.0
    var last = window.performance.now()
    val step = 1 / 60.0

    init {
        window.requestAnimationFrame { onFrame() }
    }

    private fun onFrame() {
        now = window.performance.now()
        dt += min(1.0, (now - last) / 1000.0)
        while (dt > step) {
            dt -= step
            onTick(step)
        }
        onRender(dt)
        last = now
        window.requestAnimationFrame { onFrame() }
    }

    private fun onTick(dt: Double) {
        screen?.onTick(dt)
    }

    private fun onRender(dt: Double) {
        screen?.onRender(dt)
    }

}