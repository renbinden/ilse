package uk.co.renbinden.ilse.asset

import kotlin.browser.window


class TextAsset(source: String) : Asset() {

    val text: String
        get() = textInternal
    private lateinit var textInternal: String
    override var isLoaded: Boolean = false

    init {
        window.fetch(source)
            .then { response -> response.text() }
            .then { responseText ->
                textInternal = responseText
                isLoaded = true
                onLoad()
            }
    }

}