package uk.co.renbinden.ilse.collision.event

import uk.co.renbinden.ilse.collision.RectangleCollider
import uk.co.renbinden.ilse.event.Event


class VerticalCollisionEvent(val collider: RectangleCollider, val collidingWith: List<RectangleCollider>) : Event