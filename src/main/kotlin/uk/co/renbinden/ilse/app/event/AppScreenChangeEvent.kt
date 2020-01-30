package uk.co.renbinden.ilse.app.event

import uk.co.renbinden.ilse.app.App
import uk.co.renbinden.ilse.app.screen.Screen
import uk.co.renbinden.ilse.event.Event
import uk.co.renbinden.ilse.event.EventMapper


class AppScreenChangeEvent(val app: App, val oldScreen: Screen?, val newScreen: Screen?) : Event {
    companion object : EventMapper<AppScreenChangeEvent>(AppScreenChangeEvent::class)
}