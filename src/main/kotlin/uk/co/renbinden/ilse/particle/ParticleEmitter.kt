package uk.co.renbinden.ilse.particle

import kotlin.random.Random

class ParticleEmitter(
    val effect: ParticleEffect,
    var minBurstTime: Double,
    var maxBurstTime: Double,
    var minBurstParticles: Int,
    var maxBurstParticles: Int,
    val createParticle: () -> Particle
) {

    var nextBurstTime = Random.nextDouble(minBurstTime, maxBurstTime)

    fun onTick(dt: Double) {
        nextBurstTime -= dt
        if (nextBurstTime <= 0.0) {
            nextBurstTime = Random.nextDouble(minBurstTime, maxBurstTime)
            for (i in 0..(Random.nextInt(minBurstParticles, maxBurstParticles) + 1)) {
                effect.particles.add(createParticle())
            }
        }
    }

}