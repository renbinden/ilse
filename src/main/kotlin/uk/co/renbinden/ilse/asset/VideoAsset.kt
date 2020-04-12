package uk.co.renbinden.ilse.asset

import org.w3c.dom.HTMLSourceElement
import org.w3c.dom.HTMLVideoElement
import kotlin.browser.document

class VideoAsset(vararg sources: VideoSource) : Asset() {

    constructor(sources: List<VideoSource>): this(*sources.toTypedArray())

    data class VideoSource(val source: String, val type: String)

    override var isLoaded = false
    val video = document.createElement("video") as HTMLVideoElement

    init {
        sources
            .map { (src, type) ->
                val sourceElement = document.createElement("source") as HTMLSourceElement
                sourceElement.src = src
                sourceElement.type = type
                sourceElement
            }
            .forEach { sourceElement ->
                video.appendChild(sourceElement)
            }
        video.oncanplaythrough = {
            isLoaded = true
            onLoad()
        }
    }

    fun play() {
        video.play()
    }

}