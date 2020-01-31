package uk.co.renbinden.ilse.particle

open class ParticleEffect(var life: Double) {

    val emitters = mutableListOf<ParticleEmitter>()
    val particles = mutableListOf<Particle>()

    fun onTick(dt: Double) {
        if (life <= 0.0) {
            particles.clear()
            return
        }
        emitters.forEach { emitter -> emitter.onTick(dt) }
        val deadParticles = mutableListOf<Particle>()
        particles.forEach { particle ->
            particle.onTick(dt)
            particle.life -= dt
            if (particle.life <= 0.0) {
                deadParticles.add(particle)
            }
        }
        particles.removeAll(deadParticles)
        life -= dt
    }

    fun onRender(dt: Double) {
        particles.forEach { particle -> particle.onRender(dt) }
    }

    fun emitter(
        minBurstTime: Double,
        maxBurstTime: Double,
        minBurstParticles: Int,
        maxBurstParticles: Int,
        createParticle: () -> Particle
    ) {
        emitters.add(ParticleEmitter(
            this,
            minBurstTime,
            maxBurstTime,
            minBurstParticles,
            maxBurstParticles,
            createParticle
        ))
    }

}

fun particleSystem(life: Double, init: ParticleEffect.() -> Unit): ParticleEffect {
    val particleSystem = ParticleEffect(life)
    particleSystem.init()
    return particleSystem
}