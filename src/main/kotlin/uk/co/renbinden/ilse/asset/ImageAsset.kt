package uk.co.renbinden.ilse.asset

import org.w3c.dom.HTMLImageElement
import kotlin.browser.document


class ImageAsset(source: String) : Asset() {

    val image: HTMLImageElement = (document.createElement("img") as HTMLImageElement)
        .also { it.src = source }

    override var isLoaded: Boolean = false

    init {
        image.onload = {
            isLoaded = true
            onLoad()
            true
        }
    }

}