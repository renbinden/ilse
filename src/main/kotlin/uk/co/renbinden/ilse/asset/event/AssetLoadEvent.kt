package uk.co.renbinden.ilse.asset.event

import uk.co.renbinden.ilse.asset.Asset
import uk.co.renbinden.ilse.event.Event
import uk.co.renbinden.ilse.event.EventMapper


class AssetLoadEvent(val asset: Asset) : Event {
    companion object : EventMapper<AssetLoadEvent>(AssetLoadEvent::class)
}