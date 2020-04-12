package uk.co.renbinden.ilse.particle

class Particle(var life: Double, val onTick: (Double) -> Unit, val onRender: () -> Unit)