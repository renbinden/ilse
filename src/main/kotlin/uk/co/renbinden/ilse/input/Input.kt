package uk.co.renbinden.ilse.input

import org.w3c.dom.events.KeyboardEvent
import kotlin.browser.window


object Input {

    private val keysPressed = mutableSetOf<Int>()
    val keyDownListeners = mutableListOf<(KeyboardEvent) -> Unit>()
    val keyUpListeners = mutableListOf<(KeyboardEvent) -> Unit>()
    val keyPressListeners = mutableListOf<(KeyboardEvent) -> Unit>()

    init {
        window.onkeydown = { event ->
            keysPressed.add(event.keyCode)
            keyDownListeners.forEach { it.invoke(event) }
        }

        window.onkeyup = { event ->
            keysPressed.remove(event.keyCode)
            keyUpListeners.forEach { it.invoke(event) }
        }

        window.onkeypress = { event ->
            keyPressListeners.forEach { it.invoke(event)}
        }
    }

    fun isKeyPressed(key: Int): Boolean {
        return keysPressed.contains(key)
    }

}