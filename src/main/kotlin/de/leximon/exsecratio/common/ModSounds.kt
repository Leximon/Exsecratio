package de.leximon.exsecratio.common

import de.leximon.exsecratio.common.particles.AirFlowParticle
import de.leximon.exsecratio.id
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.particle.ParticleType
import net.minecraft.sound.SoundEvent
import net.minecraft.util.registry.Registry


object ModSounds {

    val REFLECTION_HIT = registerSound("reflection_hit");


    fun init() {}

    private fun registerSound(
        name: String
    ): SoundEvent = Registry.register(Registry.SOUND_EVENT, name.id(), SoundEvent(name.id()))

}