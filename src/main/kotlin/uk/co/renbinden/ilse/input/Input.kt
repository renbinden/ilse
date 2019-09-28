package uk.co.renbinden.ilse.input

import uk.co.renbinden.ilse.event.Events
import uk.co.renbinden.ilse.input.event.*
import kotlin.browser.window
import kotlin.experimental.and


object Input {

    private val keysPressed = mutableSetOf<Int>()
    private var mouseButtonsPressed = 0.toShort()
    var mouseX: Double = 0.0
    var mouseY: Double = 0.0

    init {
        window.onkeydown = { event ->
            keysPressed.add(event.keyCode)
            Events.onEvent(KeyDownEvent(event))
        }

        window.onkeyup = { event ->
            keysPressed.remove(event.keyCode)
            Events.onEvent(KeyUpEvent(event))
        }

        window.onkeypress = { event ->
            Events.onEvent(KeyPressEvent(event))
        }

        window.onmousemove = { event ->
            mouseX = event.pageX
            mouseY = event.pageY
            Events.onEvent(MouseMoveEvent(event))
        }

        window.onmousedown = { event ->
            mouseButtonsPressed = event.buttons
            Events.onEvent(MouseDownEvent(event))
        }

        window.onmouseup = { event ->
            mouseButtonsPressed = event.buttons
            Events.onEvent(MouseUpEvent(event))
        }
    }

    fun isKeyPressed(key: Int): Boolean {
        return keysPressed.contains(key)
    }

    fun isMouseButtonPressed(button: Short): Boolean {
        return mouseButtonsPressed and button == button
    }

}