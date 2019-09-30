package uk.co.renbinden.ilse.asset

import org.w3c.xhr.XMLHttpRequest


class TextAsset(source: String) : Asset() {

    val request = XMLHttpRequest()
    val text: String
        get() = textInternal
    private lateinit var textInternal: String
    override var isLoaded: Boolean = false

    init {
        request.onreadystatechange = {
            if (request.readyState == 4.toShort() && request.status == 200.toShort()) {
                textInternal = request.responseText
                isLoaded = true
                onLoad()
            }
        }
        request.open("GET", source, true)
        request.send()
    }

}