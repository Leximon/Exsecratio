package de.leximon.exsecratio.common

import de.leximon.exsecratio.common.particles.AirFlowParticle
import de.leximon.exsecratio.id
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.particle.ParticleType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry


object ModParticles {

    val AIR_FLOW = registerParticle("air_flow", FabricParticleTypes.simple())


    fun init() {}

    @Environment(EnvType.CLIENT)
    fun initClient() {
        ParticleFactoryRegistry.getInstance().register(AIR_FLOW) { spriteProvider -> AirFlowParticle.Factory(spriteProvider) }
    }

    fun <T : ParticleType<*>> registerParticle(
        name: String,
        particle: T
    ): T = Registry.register(Registries.PARTICLE_TYPE, name.id(), particle)

//    fun <E : ParticleEffect> registerFactory(
//        particle: ParticleType<E>,
//        factory: ParticleFactory<E>
//    ) = ParticleFactoryRegistry.getInstance().register(particle, factory)
}