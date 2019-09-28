package uk.co.renbinden.ilse.input.event

import org.w3c.dom.events.EventTarget
import uk.co.renbinden.ilse.event.Event


abstract class MouseEvent(private val mouseEvent: org.w3c.dom.events.MouseEvent) : Event {

    val region: String? = mouseEvent.region
    val screenX: Int = mouseEvent.screenX
    val screenY: Int = mouseEvent.screenY
    val pageX: Double = mouseEvent.pageX
    val pageY: Double = mouseEvent.pageY
    val clientX: Int = mouseEvent.clientX
    val clientY: Int = mouseEvent.clientY
    val offsetX: Double = mouseEvent.offsetX
    val offsetY: Double = mouseEvent.offsetY
    val ctrlKey: Boolean = mouseEvent.ctrlKey
    val shiftKey: Boolean = mouseEvent.shiftKey
    val altKey: Boolean = mouseEvent.altKey
    val metaKey: Boolean = mouseEvent.metaKey
    val button: Short = mouseEvent.button
    val buttons: Short = mouseEvent.buttons
    val relatedTarget: EventTarget? = mouseEvent.relatedTarget
    fun getModifierState(keyArg: String): Boolean = mouseEvent.getModifierState(keyArg)

}