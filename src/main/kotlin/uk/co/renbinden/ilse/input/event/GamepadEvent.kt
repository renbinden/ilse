package uk.co.renbinden.ilse.input.event

import uk.co.renbinden.ilse.event.Event
import uk.co.renbinden.ilse.input.gamepad.Gamepad


abstract class GamepadEvent(val gamepad: Gamepad) : Event