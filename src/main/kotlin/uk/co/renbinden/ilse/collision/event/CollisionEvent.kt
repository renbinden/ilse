package uk.co.renbinden.ilse.collision.event

import uk.co.renbinden.ilse.collision.RectangleCollider
import uk.co.renbinden.ilse.event.Event
import uk.co.renbinden.ilse.event.EventMapper

class CollisionEvent(val collider: RectangleCollider, val collidingWith: List<RectangleCollider>) : Event {
    companion object : EventMapper<CollisionEvent>(CollisionEvent::class)
}