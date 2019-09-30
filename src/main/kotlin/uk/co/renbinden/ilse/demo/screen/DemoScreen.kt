package uk.co.renbinden.ilse.demo.screen

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import uk.co.renbinden.ilse.app.screen.Screen
import uk.co.renbinden.ilse.collision.RectangleCollider
import uk.co.renbinden.ilse.demo.assets.Assets
import uk.co.renbinden.ilse.demo.component.*
import uk.co.renbinden.ilse.demo.level.loadLevel
import uk.co.renbinden.ilse.demo.system.*
import uk.co.renbinden.ilse.ecs.engine
import uk.co.renbinden.ilse.ecs.entity.entity
import uk.co.renbinden.ilse.event.Events
import uk.co.renbinden.ilse.input.event.KeyDownEvent
import uk.co.renbinden.ilse.input.mapping.Keyboard.ARROW_LEFT
import uk.co.renbinden.ilse.input.mapping.Keyboard.ARROW_RIGHT
import uk.co.renbinden.ilse.input.mapping.Keyboard.ARROW_UP
import uk.co.renbinden.ilse.input.mapping.Keyboard.BACKTICK
import uk.co.renbinden.ilse.input.mapping.Keyboard.SPACE
import uk.co.renbinden.ilse.input.mapping.XBoxOneGamepad.Axis.DPAD_HORIZONTAL_AXIS
import uk.co.renbinden.ilse.input.mapping.XBoxOneGamepad.Axis.LEFT_STICK_HORIZONTAL_AXIS
import uk.co.renbinden.ilse.input.mapping.XBoxOneGamepad.Button.A
import kotlin.browser.document


@ExperimentalStdlibApi
class DemoScreen : Screen(
    engine {
        add(ControlSystem())
        add(AccelerationSystem())
        add(CollisionSystem())
        add(VelocitySystem())
        add(AnimationSystem())
    }
) {

    val canvas = document.getElementById("canvas") as HTMLCanvasElement
    val ctx = canvas.getContext("2d") as CanvasRenderingContext2D

    var debug = false

    init {
        loadLevel(Assets.Maps.level1) { obj, x, y ->
            when (obj) {
                'b' -> {
                    engine.add(entity {
                        add(Position(x, y))
                        add(Dimensions(32.0, 32.0))
                        add(Image(Assets.Images.block))
                        add(Collider(RectangleCollider(
                            get(Position)::x,
                            get(Position)::y,
                            get(Dimensions)::width,
                            get(Dimensions)::height
                        )))
                    })
                }
                'P' -> {
                    engine.add(entity {
                        add(Position(x, y, 0.0, 768.0, 0.0, 568.0))
                        add(Velocity(0.0, 0.0, 240.0, 720.0))
                        add(Acceleration(0.0, 320.0, 0.0, 32.0))
                        add(Dimensions(32.0, 32.0))
                        add(Collider(RectangleCollider(
                            get(Position)::x,
                            get(Position)::y,
                            get(Dimensions)::width,
                            get(Dimensions)::height
                        )))
                        add(Animation(Assets.Animations.catWalkRight, 0.5))
                        add(
                            Controls(
                                leftKey = ARROW_LEFT,
                                rightKey = ARROW_RIGHT,
                                jumpKey = ARROW_UP,
                                gamepadHorizontalAxes = arrayOf(
                                    LEFT_STICK_HORIZONTAL_AXIS,
                                    DPAD_HORIZONTAL_AXIS
                                ),
                                gamepadJumpButton = A
                            )
                        )
                    })
                }
            }
        }

        Events.addListener(KeyDownEvent::class) { event ->
            when (event.keyCode) {
                SPACE -> Assets.Sounds.coins.play()
                BACKTICK -> {
                    debug = !debug
                }
            }
        }
    }

    override fun onRender(dt: Double) {
        ctx.clearRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
        ctx.fillStyle = "rgb(0, 0, 0)"
        ctx.fillRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
        engine.entities
            .filter { entity -> entity.has(Position) && entity.has(Image) }
            .forEach { entity ->
                val position = entity[Position]
                val image = entity[Image]
                if (image.asset.isLoaded) {
                    ctx.drawImage(image.asset.image, position.x, position.y)
                }
            }

        engine.entities
            .filter { entity -> entity.has(Position) && entity.has(Animation) }
            .forEach { entity ->
                val position = entity[Position]
                val animation = entity[Animation]
                if (animation.asset.isLoaded) {
                    animation.asset.drawFrame(animation.getFrame(), ctx, position.x, position.y)
                }
            }

        if (debug) {
            engine.entities
                .filter { entity -> entity.has(Collider) }
                .forEach { entity ->
                    val collider = entity[Collider].collider
                    ctx.strokeStyle = "rgb(255, 0, 0)"
                    ctx.strokeRect(collider.x, collider.y, collider.width, collider.height)
                }
        }
    }

}