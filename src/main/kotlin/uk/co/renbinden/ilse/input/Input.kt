package uk.co.renbinden.ilse.input

import org.w3c.dom.events.GamepadEvent
import uk.co.renbinden.ilse.event.Events
import uk.co.renbinden.ilse.input.event.*
import uk.co.renbinden.ilse.input.gamepad.Gamepad
import kotlin.browser.window
import kotlin.experimental.and


object Input {

    private val keysPressed = mutableSetOf<Int>()
    private var mouseButtonsPressed = 0.toShort()
    var mouseX: Double = 0.0
    var mouseY: Double = 0.0
    val gamepads: List<Gamepad> = mutableListOf()

    init {
        window.onkeydown = { event ->
            keysPressed.add(event.keyCode)
            Events.onEvent(KeyDownEvent(event))
        }

        window.onkeyup = { event ->
            keysPressed.remove(event.keyCode)
            Events.onEvent(KeyUpEvent(event))
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

        window.addEventListener("gamepadconnected", { event ->
            val gamepadEvent = event as GamepadEvent
            val gamepad = Gamepad(gamepadEvent.gamepad.index)
            (gamepads as MutableList<Gamepad>).add(gamepad)
            Events.onEvent(GamepadConnectEvent(gamepad))
        })

        window.addEventListener("gamepaddisconnected", { event ->
            val gamepadEvent = event as GamepadEvent
            val gamepad = gamepads.firstOrNull { gamepad -> gamepad.index == gamepadEvent.gamepad.index }
            if (gamepad != null) {
                (gamepads as MutableList<Gamepad>).remove(gamepad)
                Events.onEvent(GamepadDisconnectEvent(gamepad))
            }
        })
    }

    fun isKeyPressed(key: Int): Boolean {
        return keysPressed.contains(key)
    }

    fun isMouseButtonPressed(button: Short): Boolean {
        return mouseButtonsPressed and button == button
    }

    fun pollGamepads() {
        gamepads.forEach(Gamepad::poll)
    }

}