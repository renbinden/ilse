package uk.co.renbinden.ilse.demo.assets

import uk.co.renbinden.ilse.asset.AnimationAsset
import uk.co.renbinden.ilse.asset.ImageAsset
import uk.co.renbinden.ilse.asset.SoundAsset
import uk.co.renbinden.ilse.asset.TextAsset


object Assets {

    object Sounds {
        val coins = SoundAsset("static/sounds/coin.wav")
    }

    object Images {
        val block = ImageAsset("static/images/block.png")
    }

    object Animations {
        val catWalkRight = AnimationAsset("static/images/cat_walk_right.png", 32, 32)
        val catWalkLeft = AnimationAsset("static/images/cat_walk_left.png", 32, 32)
    }

    object Maps {
        val level1 = TextAsset("static/levels/test_level.txt")
    }

}