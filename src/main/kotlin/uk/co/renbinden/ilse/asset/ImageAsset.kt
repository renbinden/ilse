package uk.co.renbinden.ilse.asset

import org.w3c.dom.HTMLImageElement
import kotlin.browser.document


class ImageAsset(source: String) : Asset() {

    val image: HTMLImageElement = (document.createElement("img") as HTMLImageElement)
        .also { it.src = source }

    private var loaded = false

    init {
        image.onload = {
            loaded = true
            true
        }
    }

    override fun isLoaded(): Boolean {
        return loaded
    }

}