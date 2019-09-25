package uk.co.renbinden.ilse.demo.assets

import uk.co.renbinden.ilse.asset.AnimationAsset
import uk.co.renbinden.ilse.asset.ImageAsset
import uk.co.renbinden.ilse.asset.SoundAsset


object Assets {

    object Sounds {
        val coins = SoundAsset("static/sounds/coin.wav")
    }

    object Images {
        val triangle = ImageAsset("static/images/triangle.png")
    }

    object Animations {
        val triangle = AnimationAsset("static/images/triangle.png", 32, 32)
    }

}