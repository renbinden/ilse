package uk.co.renbinden.ilse.app.event

import uk.co.renbinden.ilse.app.App
import uk.co.renbinden.ilse.app.screen.Screen
import uk.co.renbinden.ilse.event.Event


class AppScreenChangeEvent(val app: App, val oldScreen: Screen?, val newScreen: Screen?) : Event