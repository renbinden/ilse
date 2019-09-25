package uk.co.renbinden.ilse.demo.screen

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import uk.co.renbinden.ilse.app.screen.Screen
import uk.co.renbinden.ilse.demo.assets.Assets
import uk.co.renbinden.ilse.demo.component.*
import uk.co.renbinden.ilse.demo.system.AccelerationSystem
import uk.co.renbinden.ilse.demo.system.AnimationSystem
import uk.co.renbinden.ilse.demo.system.ControlSystem
import uk.co.renbinden.ilse.demo.system.VelocitySystem
import uk.co.renbinden.ilse.ecs.engine
import uk.co.renbinden.ilse.ecs.entity.entity
import uk.co.renbinden.ilse.input.Input
import kotlin.browser.document


class DemoScreen : Screen(
    engine {
        add(ControlSystem())
        add(AccelerationSystem())
        add(VelocitySystem())
        add(AnimationSystem())
        add(entity {
            add(Position(16.0, 16.0, 0.0, 768.0, 0.0, 568.0))
            add(Velocity(0.0, 0.0, 4.0, 4.0))
            add(Acceleration(0.0, 0.0, 2.0, 2.0))
            add(Dimensions(32.0, 32.0))
            add(Animation(Assets.Animations.triangle, 0.5))
            add(Controls(
                leftKey = 37,
                rightKey = 39,
                upKey = 38,
                downKey = 40
            ))
        })
    }
) {

    val canvas = document.getElementById("canvas") as HTMLCanvasElement
    val ctx = canvas.getContext("2d") as CanvasRenderingContext2D

    init {
        Input.keyPressListeners.add { event ->
            if (event.keyCode == 32) {
                Assets.Sounds.coins.play()
            }
        }
    }

    override fun onRender(dt: Double) {
        ctx.clearRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
        ctx.fillStyle = "rgb(0, 0, 0)"
        ctx.fillRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
        engine.entities
            .filter { entity -> entity.has(Position) && entity.has(Animation) }
            .forEach { entity ->
                val position = entity[Position]
                val animation = entity[Animation]
                if (animation.asset.isLoaded()) {
                    animation.asset.drawFrame(animation.getFrame(), ctx, position.x, position.y)
                }
            }
    }

}