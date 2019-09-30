package uk.co.renbinden.ilse.input.mapping


object Keyboard {

    const val BACKSPACE = 8
    const val TAB = 9
    const val ENTER = 13
    const val SHIFT = 16
    const val CONTROL = 17
    const val ALT = 18
    const val PAUSE = 19
    const val CAPS_LOCK = 20
    const val ESC = 27
    const val SPACE = 32
    const val PAGE_UP = 33
    const val PAGE_DOWN = 34
    const val END = 35
    const val HOME = 36
    const val ARROW_LEFT = 37
    const val ARROW_UP = 38
    const val ARROW_RIGHT = 39
    const val ARROW_DOWN = 40
    const val INSERT = 45
    const val DELETE = 46
    const val ZERO = 48
    const val ONE = 49
    const val TWO = 50
    const val THREE = 51
    const val FOUR = 52
    const val FIVE = 53
    const val SIX = 54
    const val SEVEN = 55
    const val EIGHT = 56
    const val NINE = 57
    const val SEMICOLON = 59
    const val EQUALS = 61
    const val A = 65
    const val B = 66
    const val C = 67
    const val D = 68
    const val E = 69
    const val F = 70
    const val G = 71
    const val H = 72
    const val I = 73
    const val J = 74
    const val K = 75
    const val L = 76
    const val M = 77
    const val N = 78
    const val O = 79
    const val P = 80
    const val Q = 81
    const val R = 82
    const val S = 83
    const val T = 84
    const val U = 85
    const val V = 86
    const val W = 87
    const val X = 88
    const val Y = 89
    const val Z = 90
    const val F1 = 112
    const val F2 = 113
    const val F3 = 114
    const val F4 = 115
    const val F5 = 116
    const val F6 = 117
    const val F7 = 118
    const val F8 = 119
    const val F9 = 120
    const val F10 = 121
    const val F11 = 122
    const val F12 = 123
    const val NUM_LOCK = 144
    const val SCROLL_LOCK = 145
    const val HASH = 163
    const val MINUS = 173
    const val COMMA = 188
    const val DOT = 190
    const val SLASH = 191
    const val BACKTICK = 192
    const val OPEN_SQUARE_BRACKET = 219
    const val BACKSLASH = 220
    const val CLOSE_SQUARE_BRACKET = 221
    const val APOSTROPHE = 222
    const val ALT_GRAPH =  225

    fun toString(keyCode: Int): String {
        return when (keyCode) {
            8 -> "Backspace"
            9 -> "Tab"
            13 -> "Enter"
            16 -> "Shift"
            17 -> "Control"
            18 -> "Alt"
            19 -> "Pause"
            20 -> "Caps Lock"
            27 -> "Escape"
            32 -> "Space"
            33 -> "Page Up"
            34 -> "Page Down"
            35 -> "End"
            36 -> "Home"
            37 -> "Left Arrow"
            38 -> "Up Arrow"
            39 -> "Right Arrow"
            40 -> "Down Arrow"
            45 -> "Insert"
            46 -> "Delete"
            48 -> "0"
            49 -> "1"
            50 -> "2"
            51 -> "3"
            52 -> "4"
            53 -> "5"
            54 -> "6"
            55 -> "7"
            56 -> "8"
            57 -> "9"
            59 -> ";"
            61 -> "="
            65 -> "A"
            66 -> "B"
            67 -> "C"
            68 -> "D"
            69 -> "E"
            70 -> "F"
            71 -> "G"
            72 -> "H"
            73 -> "I"
            74 -> "J"
            75 -> "K"
            76 -> "L"
            77 -> "M"
            78 -> "N"
            79 -> "O"
            80 -> "P"
            81 -> "Q"
            82 -> "R"
            83 -> "S"
            84 -> "T"
            85 -> "U"
            86 -> "V"
            87 -> "W"
            88 -> "X"
            89 -> "Y"
            90 -> "Z"
            112 -> "F1"
            113 -> "F2"
            114 -> "F3"
            115 -> "F4"
            116 -> "F5"
            117 -> "F6"
            118 -> "F7"
            119 -> "F8"
            120 -> "F9"
            121 -> "F10"
            122 -> "F11"
            123 -> "F12"
            144 -> "Num Lock"
            145 -> "Scroll Lock"
            163 -> "#"
            173 -> "-"
            188 -> ","
            190 -> "."
            191 -> "/"
            192 -> "`"
            219 -> "["
            220 -> "\\"
            221 -> "]"
            222 -> "'"
            225 -> "Alt Graph"
            else -> "Unknown"
        }
    }

}