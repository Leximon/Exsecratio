package de.leximon.exsecratio.common.enchantments

import de.leximon.exsecratio.mixin.extensions.EnchantmentExtension
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot

object AerodynamicsPactEnchantment : Enchantment(Rarity.RARE, EnchantmentTarget.BOW, arrayOf(EquipmentSlot.MAINHAND)), EnchantmentExtension {

    override fun getMinPower(level: Int) = 25
    override fun getMaxPower(level: Int) = 50
    override fun getMaxLevel() = 1

    override fun isTreasure() = true
    override fun isAvailableForEnchantedBookOffer() = false
    override fun isPact() = true
}