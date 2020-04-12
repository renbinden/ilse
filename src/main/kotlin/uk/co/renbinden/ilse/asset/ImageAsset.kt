package uk.co.renbinden.ilse.asset

import org.w3c.dom.CanvasImageSource
import org.w3c.dom.HTMLImageElement
import kotlin.browser.document


class ImageAsset(val image: HTMLImageElement) : Asset(), CanvasImageSource by image {

    constructor(source: String): this(
        (document.createElement("img") as HTMLImageElement).also { it.src = source }
    )

    override var isLoaded: Boolean = false

    init {
        image.onload = {
            isLoaded = true
            onLoad()
            true
        }
    }

}