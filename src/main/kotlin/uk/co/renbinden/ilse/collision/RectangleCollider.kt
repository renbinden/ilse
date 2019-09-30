package uk.co.renbinden.ilse.collision

import uk.co.renbinden.ilse.collision.event.HorizontalCollisionEvent
import uk.co.renbinden.ilse.collision.event.VerticalCollisionEvent
import uk.co.renbinden.ilse.event.Events
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty0


class RectangleCollider(
    x: KMutableProperty0<Double>,
    y: KMutableProperty0<Double>,
    width: KProperty0<Double>,
    height: KProperty0<Double>
) {

    private var _x: KMutableProperty0<Double> = x
    private var _y: KMutableProperty0<Double> = y
    private var _width: KProperty0<Double> = width
    private var _height: KProperty0<Double> = height

    var x: Double
        get() = _x.get()
        set(value) = _x.set(value)

    var y: Double
        get() = _y.get()
        set(value) = _y.set(value)

    val width: Double
        get() = _width.get()

    val height: Double
        get() = _height.get()

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
                Events.onEvent(HorizontalCollisionEvent(this@RectangleCollider, horizontalCollisions))
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
                Events.onEvent(VerticalCollisionEvent(this@RectangleCollider, verticalCollisions))
                return true
            }
            return false
        }

    }

}