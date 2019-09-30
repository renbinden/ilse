package org.w3c.dom.gamepad


external class Gamepad {

    val id: String
    val index: Long
    val connected: Boolean
    val timestamp: Double
    val mapping: String
    val axes: Array<Double>
    val buttons: Array<GamepadButton>
    var previousTimestamp: Double

}