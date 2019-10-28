package uk.co.renbinden.ilse.tiled

import uk.co.renbinden.ilse.color.Color
import uk.co.renbinden.ilse.tiled.HAlign.LEFT
import uk.co.renbinden.ilse.tiled.VAlign.TOP


class Text(
    fontFamily: String? = null,
    pixelSize: Int? = null,
    wrap: Boolean? = null,
    color: Color? = null,
    bold: Boolean? = null,
    italic: Boolean? = null,
    underline: Boolean? = null,
    strikeout: Boolean? = null,
    kerning: Boolean? = null,
    halign: HAlign? = null,
    valign: VAlign? = null
) {

    val fontFamily = fontFamily ?: "sans-serif"
    val pixelSize = pixelSize ?: 16
    val wrap = wrap ?: false
    val color = color ?: Color(0, 0, 0)
    val bold = bold ?: false
    val italic = italic ?: false
    val underline = underline ?: false
    val strikeout = strikeout ?: false
    val kerning = kerning ?: false
    val halign = halign ?: LEFT
    val valign = valign ?: TOP

}