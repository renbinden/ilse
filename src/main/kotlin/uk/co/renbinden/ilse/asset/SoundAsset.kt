package uk.co.renbinden.ilse.asset

import org.w3c.dom.Audio


class SoundAsset(val source: String, volume: Double = 1.0, var loop: Boolean = false) : Asset() {

    private val audioInstances = mutableListOf<Audio>()
    override val isLoaded: Boolean = true

    var volume: Double = volume
        set(value) {
            audioInstances.forEach { it.volume = value }
            field = value
        }

    init {
        onLoad()
    }

    fun play() {
        val stoppedAudios = audioInstances.filter { audio -> audio.paused || audio.currentTime <= 0 || audio.ended  }
        if (stoppedAudios.isEmpty()) {
            val audio = Audio(source)
            audio.volume = volume
            audio.loop = loop
            audioInstances.add(audio)
            play()
            return
        }
        stoppedAudios.first().play()
    }

}