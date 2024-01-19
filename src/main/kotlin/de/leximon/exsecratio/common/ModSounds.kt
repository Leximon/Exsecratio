package de.leximon.exsecratio.common

import de.leximon.exsecratio.id
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent


object ModSounds {

    val REFLECTION_HIT = registerSound("reflection_hit");


    fun init() {}

    private fun registerSound(
        name: String
    ): SoundEvent = Registry.register(Registries.SOUND_EVENT, name.id(), SoundEvent.of(name.id()))

}