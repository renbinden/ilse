package uk.co.renbinden.ilse.asset

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLImageElement
import kotlin.browser.document
import kotlin.properties.Delegates


class AnimationAsset(val source: String, val frameWidth: Int, val frameHeight: Int) : Asset() {

    val image = (document.createElement("img") as HTMLImageElement)
        .also { it.src = source }

    private var rows by Delegates.notNull<Int>()
    private var cols by Delegates.notNull<Int>()
    var frames by Delegates.notNull<Int>()
    private var loaded = false

    init {
        image.onload = {
            rows = image.height / frameHeight
            cols = image.width / frameWidth
            frames = rows * cols
            loaded = true
            true
        }
    }

    override fun isLoaded(): Boolean {
        return loaded
    }

    fun drawFrame(index: Int, canvas: CanvasRenderingContext2D, x: Double, y: Double) {
        val frameX = index.rem(cols) * frameWidth
        val frameY = index.div(cols) * frameHeight
        canvas.drawImage(
            image,
            frameX.toDouble(),
            frameY.toDouble(),
            frameWidth.toDouble(),
            frameHeight.toDouble(),
            x,
            y,
            frameWidth.toDouble(),
            frameHeight.toDouble()
        )
    }

}