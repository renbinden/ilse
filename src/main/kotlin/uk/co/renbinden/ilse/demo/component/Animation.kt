package uk.co.renbinden.ilse.demo.component

import uk.co.renbinden.ilse.asset.AnimationAsset
import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.component.ComponentMapper
import kotlin.math.floor
import kotlin.math.roundToInt


class Animation(var asset: AnimationAsset, val frameDuration: Double) : Component {

    var t = 0.0

    fun getFrame(): Int {
        return floor(t.div(frameDuration).rem(asset.frames)).roundToInt()
    }

    companion object: ComponentMapper<Animation>(Animation::class)

}