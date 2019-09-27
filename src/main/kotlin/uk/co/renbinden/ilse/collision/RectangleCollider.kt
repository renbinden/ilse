package uk.co.renbinden.ilse.collision


class RectangleCollider(
    private var _x: Double,
    private var _y: Double,
    private var _width: Double,
    private var _height: Double
) {

    private var getX: () -> Double = { _x }
    private var setX: (Double) -> Unit = { _x = it }
    private var getY: () -> Double = { _y }
    private var setY: (Double) -> Unit = { _y = it }
    private var getWidth: () -> Double = { _width }
    private var getHeight: () -> Double = { _height }

    var x: Double
        get() = getX()
        set(value) = setX(value)

    var y: Double
        get() = getY()
        set(value) = setY(value)

    val width: Double
        get() = getWidth()

    val height: Double
        get() = getHeight()

    constructor(
        getX: () -> Double,
        setX: (Double) -> Unit,
        getY: () -> Double,
        setY: (Double) -> Unit,
        getWidth: () -> Double,
        getHeight: () -> Double
    ): this(0.0, 0.0, 0.0, 0.0) {
        this.getX = getX
        this.setX = setX
        this.getY = getY
        this.setY = setY
        this.getWidth = getWidth
        this.getHeight = getHeight
    }

    fun test(x: Double = this.x, y: Double = this.y): TestPosition {
        return TestPosition(x, y)
    }

    fun collidesWith(other: RectangleCollider): Boolean {
        return test(x, y).collidesWith(other)
    }

    fun collidesWith(others: List<RectangleCollider>): Boolean {
        return test(x, y).collidesWith(others)
    }

    fun getCollisions(others: List<RectangleCollider>): List<RectangleCollider> {
        return test(x, y).getCollisions(others)
    }

    inner class TestPosition(val testX: Double, val testY: Double) {

        fun collidesWith(
            other: RectangleCollider
        ): Boolean {
            return testX < other.x + other.width
                    && testX + width > other.x
                    && testY < other.y + other.height
                    && testY + height > other.y
        }

        fun collidesWith(others: List<RectangleCollider>): Boolean {
            return others.any { collidesWith(it) }
        }

        fun getCollisions(others: List<RectangleCollider>): List<RectangleCollider> {
            return others.filter { collidesWith(it) }
        }

        fun resolveHorizontal(others: List<RectangleCollider>): Boolean {
            val dx = testX - x
            val horizontalCollisions = getCollisions(others)
            if (horizontalCollisions.isNotEmpty()) {
                if (dx > 0) {
                    val other = horizontalCollisions.minBy { it.x }
                    if (other != null) {
                        x = other.x - width
                    }
                }
                if (dx < 0) {
                    val other = horizontalCollisions.maxBy { otherCollider -> otherCollider.x + otherCollider.width }
                    if (other != null) {
                        x = other.x + other.width
                    }
                }
                return true
            }
            return false
        }

        fun resolveVertical(others: List<RectangleCollider>): Boolean {
            val dy = testY - y
            val verticalCollisions = getCollisions(others)
            if (verticalCollisions.isNotEmpty()) {
                if (dy > 0) {
                    val other = verticalCollisions.minBy(RectangleCollider::y)
                    if (other != null) {
                        y = other.y - height
                    }
                }
                if (dy < 0) {
                    val other = verticalCollisions.maxBy { otherCollider -> otherCollider.y + otherCollider.height }
                    if (other != null) {
                        y = other.y + other.height
                    }
                }
                return true
            }
            return false
        }

    }

}