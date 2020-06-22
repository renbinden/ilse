package uk.co.renbinden.ilse.tiled

import uk.co.renbinden.ilse.asset.Asset
import uk.co.renbinden.ilse.color.Color

interface Type<T> {

    fun coerce(value: String): T

    companion object {

        private class StringType: Type<String> {
            override fun coerce(value: String): String {
                return value
            }
        }

        private class IntType: Type<Int> {
            override fun coerce(value: String): Int {
                return value.toInt()
            }
        }

        private class FloatType: Type<Float> {
            override fun coerce(value: String): Float {
                return value.toFloat()
            }
        }

        private class BoolType: Type<Boolean> {
            override fun coerce(value: String): Boolean {
                return value.toBoolean()
            }
        }

        private class ColorType: Type<Color> {
            override fun coerce(value: String): Color {
                return Color.fromARGBHexCode(value) ?: throw IllegalArgumentException("Cannot coerce property to Color")
            }
        }

        private class FileType: Type<Asset> {
            override fun coerce(value: String): Asset {
                throw UnsupportedOperationException("not implemented")
            }
        }

        val STRING: Type<String> = StringType()
        val INT: Type<Int> = IntType()
        val FLOAT: Type<Float> = FloatType()
        val BOOL: Type<Boolean> = BoolType()
        val COLOR: Type<Color> = ColorType()
        val FILE: Type<Asset> = FileType()

        fun valueOf(value: String): Type<out Any> {
            return when (value) {
                "STRING" -> STRING
                "INT" -> INT
                "FLOAT" -> FLOAT
                "BOOL" -> BOOL
                "COLOR" -> COLOR
                "FILE" -> FILE
                "OBJECT" -> throw UnsupportedOperationException("not implemented")
                else -> throw IllegalArgumentException()
            }
        }

    }

}