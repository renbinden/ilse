package uk.co.renbinden.ilse.asset

import org.w3c.dom.Audio


class SoundAsset(val source: String) : Asset() {

    private val audioInstances = mutableListOf<Audio>()
    override val isLoaded: Boolean = true

    init {
        onLoad()
    }

    fun play() {
        val stoppedAudios = audioInstances.filter { audio -> audio.paused || audio.currentTime <= 0 || audio.ended  }
        if (stoppedAudios.isEmpty()) {
            audioInstances.add(Audio(source))
            play()
            return
        }
        stoppedAudios.first().play()
    }

}