package uk.co.renbinden.ilse.input.event

import uk.co.renbinden.ilse.event.EventMapper
import uk.co.renbinden.ilse.input.gamepad.Gamepad


class GamepadConnectEvent(gamepad: Gamepad) : GamepadEvent(gamepad) {
    companion object : EventMapper<GamepadConnectEvent>(GamepadConnectEvent::class)
}