package de.leximon.exsecratio.common

import de.leximon.exsecratio.common.enchantments.AccelerationPactEnchantment
import de.leximon.exsecratio.common.enchantments.AerodynamicsPactEnchantment
import de.leximon.exsecratio.common.enchantments.BloodPactEnchantment
import de.leximon.exsecratio.common.enchantments.DecayCurseEnchantment
import de.leximon.exsecratio.id
import net.minecraft.enchantment.Enchantment
import net.minecraft.util.registry.Registry

object ModEnchantments {

    fun init() {
        registerEnchantment("decay_curse", DecayCurseEnchantment)
        registerEnchantment("blood_pact", BloodPactEnchantment)
        registerEnchantment("acceleration_pact", AccelerationPactEnchantment)
        registerEnchantment("aerodynamics_pact", AerodynamicsPactEnchantment)
    }

    private fun registerEnchantment(
        name: String,
        enchantment: Enchantment
    ) = Registry.register(Registry.ENCHANTMENT, name.id(), enchantment)
}