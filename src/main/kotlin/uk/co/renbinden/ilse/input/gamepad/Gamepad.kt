package uk.co.renbinden.ilse.input.gamepad

import uk.co.renbinden.ilse.event.Events
import uk.co.renbinden.ilse.input.event.GamepadButtonDownEvent
import uk.co.renbinden.ilse.input.event.GamepadButtonUpEvent
import kotlin.browser.window


class Gamepad(val index: Long) {

    var pressedButtons = setOf<Int>()

    private val jsGamepad: org.w3c.dom.gamepad.Gamepad
        get() = window.navigator.asDynamic().getGamepads()[index] as org.w3c.dom.gamepad.Gamepad

    val isConnected: Boolean
        get() = jsGamepad.connected

    fun isButtonPressed(button: Int): Boolean {
        return jsGamepad.buttons[button].pressed
    }

    fun getButtonValue(button: Int): Double {
        return jsGamepad.buttons[button].value
    }

    fun getAxisValue(axis: Int): Double {
        return jsGamepad.axes[axis]
    }

    fun poll() {
        val newPressedButtons = jsGamepad.buttons
            .mapIndexed { index, button -> Pair(index, button) }
            .filter { (_, button) -> button.pressed }
            .map { (index, _) -> index }
            .toSet()
        val newlyPressedButtons = newPressedButtons.filter { !pressedButtons.contains(it) }
        val newlyReleasedButtons = pressedButtons.filter { !newPressedButtons.contains(it) }
        pressedButtons = newPressedButtons
        newlyPressedButtons.forEach { Events.onEvent(GamepadButtonDownEvent(this, it)) }
        newlyReleasedButtons.forEach { Events.onEvent(GamepadButtonUpEvent(this, it)) }
    }

}