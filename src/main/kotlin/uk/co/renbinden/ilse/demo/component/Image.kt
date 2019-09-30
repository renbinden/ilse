package uk.co.renbinden.ilse.demo.component

import uk.co.renbinden.ilse.asset.ImageAsset
import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.component.ComponentMapper


class Image(val asset: ImageAsset) : Component {

    companion object: ComponentMapper<Image>(Image::class)

}