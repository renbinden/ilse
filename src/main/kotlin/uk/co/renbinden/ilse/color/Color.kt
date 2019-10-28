package uk.co.renbinden.ilse.color


class Color(
    val red: Int,
    val green: Int,
    val blue: Int,
    val alpha: Int = 255
) {

    fun toRGBAHexCode(): String {
        return "#${red.toString(16)}${green.toString(16)}${blue.toString(16)}${alpha.toString(16)}"
    }

    fun toRGBHexCode(): String {
        return "#${red.toString(16)}${green.toString(16)}${blue.toString(16)}"
    }

    fun toARGBHexCode(): String {
        return "#${alpha.toString(16)}${red.toString(16)}${green.toString(16)}${blue.toString(16)}"
    }

    companion object {
        fun fromRGBAHexCode(rgba: String): Color? {
            if (rgba.isEmpty()) return null
            val startIndex = if (rgba.startsWith('#')) 1 else 0
            if (rgba.length - startIndex % 3 == 0) return fromRGBHexCode(rgba)
            if (rgba.length - startIndex == 4)
                return fromRGBAHexCode(
                    rgba.substring(startIndex, startIndex + 1).repeat(2) +
                            rgba.substring(startIndex + 1, startIndex + 2).repeat(2) +
                            rgba.substring(startIndex + 2, startIndex + 3).repeat(2) +
                            rgba.substring(startIndex + 3, startIndex + 4).repeat(2)
                )
            val red = rgba.substring(startIndex, startIndex + 2).toInt(16)
            val green = rgba.substring(startIndex + 2, startIndex + 4).toInt(16)
            val blue = rgba.substring(startIndex + 4, startIndex + 6).toInt(16)
            val alpha = rgba.substring(startIndex + 6, startIndex + 8).toInt(16)
            return Color(red, green, blue, alpha)
        }

        fun fromRGBHexCode(rgb: String): Color? {
            if (rgb.isEmpty()) return null
            val startIndex = if (rgb.startsWith('#')) 1 else 0
            if (rgb.length - startIndex == 3) {
                return fromRGBHexCode(
                    rgb.substring(startIndex, startIndex + 1).repeat(2) +
                            rgb.substring(startIndex + 1, startIndex + 2).repeat(2) +
                            rgb.substring(startIndex + 2, startIndex + 3).repeat(2)
                )
            }
            val red = rgb.substring(startIndex, startIndex + 2).toInt(16)
            val green = rgb.substring(startIndex + 2, startIndex + 4).toInt(16)
            val blue = rgb.substring(startIndex + 4, startIndex + 6).toInt(16)
            return Color(red, green, blue)
        }

        fun fromARGBHexCode(argb: String): Color? {
            if (argb.isEmpty()) return null
            val startIndex = if (argb.startsWith('#')) 1 else 0
            if (argb.length - startIndex % 3 == 0) return fromRGBHexCode(argb)
            if (argb.length - startIndex == 4)
                return fromARGBHexCode(
                    argb.substring(startIndex, startIndex + 1).repeat(2) +
                            argb.substring(startIndex + 1, startIndex + 2).repeat(2) +
                            argb.substring(startIndex + 2, startIndex + 3).repeat(2) +
                            argb.substring(startIndex + 3, startIndex + 4).repeat(2)
                )
            val alpha = argb.substring(startIndex, startIndex + 2).toInt(16)
            val red = argb.substring(startIndex + 2, startIndex + 4).toInt(16)
            val green = argb.substring(startIndex + 4, startIndex + 6).toInt(16)
            val blue = argb.substring(startIndex + 6, startIndex + 8).toInt(16)
            return Color(red, green, blue, alpha)
        }
    }

}