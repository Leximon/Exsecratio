package de.leximon.exsecratio.common

import de.leximon.exsecratio.common.enchantments.AccelerationPactEnchantment
import de.leximon.exsecratio.common.enchantments.AerodynamicsPactEnchantment
import de.leximon.exsecratio.common.enchantments.BloodPactEnchantment
import de.leximon.exsecratio.common.enchantments.DecayCurseEnchantment
import de.leximon.exsecratio.id
import net.minecraft.enchantment.Enchantment
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object ModEnchantments {
    private val DECAY_CURSE = registerEnchantment("decay_curse", DecayCurseEnchantment)
    private val BLOOD_PACT = registerEnchantment("blood_pact", BloodPactEnchantment)
    private val ACCELERATION_PACT = registerEnchantment("acceleration_pact", AccelerationPactEnchantment)
    private val AERODYNAMICS_PACT = registerEnchantment("aerodynamics_pact", AerodynamicsPactEnchantment)

    fun init() {}

    private fun registerEnchantment(
        name: String,
        enchantment: Enchantment
    ) = Registry.register(Registries.ENCHANTMENT, name.id(), enchantment)
}