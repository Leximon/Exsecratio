package de.leximon.exsecratio.common

import de.leximon.exsecratio.common.enchantments.*
import de.leximon.exsecratio.id
import net.minecraft.enchantment.Enchantment
import net.minecraft.util.registry.Registry

object ModEnchantments {

    fun init() {
        registerEnchantment("decay_curse", DecayCurseEnchantment)
        registerEnchantment("reflection_curse", ReflectionCurseEnchantment)
        registerEnchantment("blood_pact", BloodPactEnchantment)
        registerEnchantment("acceleration_pact", AccelerationPactEnchantment)
        registerEnchantment("aerodynamics_pact", AerodynamicsPactEnchantment)
    }

    private fun registerEnchantment(
        name: String,
        enchantment: Enchantment
    ) = Registry.register(Registry.ENCHANTMENT, name.id(), enchantment)
}