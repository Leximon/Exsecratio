package de.leximon.exsecratio.common.particles

import net.minecraft.client.particle.ParticleFactory
import net.minecraft.client.particle.ParticleTextureSheet
import net.minecraft.client.particle.SpriteBillboardParticle
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.DefaultParticleType
import net.minecraft.util.math.MathHelper

class AirFlowParticle(
    world: ClientWorld,
    x: Double, y: Double, z: Double,
    velocityX: Double, velocityY: Double, velocityZ: Double,
    private val spriteProvider: SpriteProvider
) : SpriteBillboardParticle(world, x, y, z) {

    init {
        maxAge = 14
        collidesWithWorld = false
        this.velocityX = velocityX
        this.velocityY = velocityY
        this.velocityZ = velocityZ
        setSpriteForAge(spriteProvider)
    }

    override fun tick() {
        super.tick()
        if (!dead) {
            alpha = (1f - (age.toFloat() / maxAge.toFloat())) * 0.8f
            setSpriteForAge(spriteProvider)
        }
    }

    override fun getType(): ParticleTextureSheet = ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT

    override fun getSize(tickDelta: Float) = MathHelper.clamp((age.toFloat() + tickDelta) / maxAge.toFloat(), 0f, 1f) * 0.5f

    class Factory(private val spriteProvider: SpriteProvider) : ParticleFactory<DefaultParticleType?> {
        override fun createParticle(
            defaultParticleType: DefaultParticleType?, clientWorld: ClientWorld,
            x: Double, y: Double, z: Double,
            velocityX: Double, velocityY: Double, velocityZ: Double
        ) = AirFlowParticle(clientWorld, x, y, z, velocityX, velocityY, velocityZ, spriteProvider)
    }
}