package uk.co.renbinden.ilse.asset

import uk.co.renbinden.ilse.asset.event.AssetLoadEvent
import uk.co.renbinden.ilse.event.Events


abstract class Asset {

    abstract val isLoaded: Boolean

    protected fun onLoad() {
        Events.onEvent(AssetLoadEvent(this))
    }

}