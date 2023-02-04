package de.leximon.exsecratio.common.enchantments

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot

object DecayCurseEnchantment : Enchantment(Rarity.VERY_RARE, EnchantmentTarget.BREAKABLE, EquipmentSlot.values()) {

    override fun getMinPower(level: Int) = 25
    override fun getMaxPower(level: Int) = 50
    override fun getMaxLevel() = 1

    override fun isTreasure() = true
    override fun isCursed() = true
}
