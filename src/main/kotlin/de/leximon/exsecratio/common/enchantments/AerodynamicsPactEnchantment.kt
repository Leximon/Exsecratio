package de.leximon.exsecratio.common.enchantments

import de.leximon.exsecratio.mixin.extensions.EnchantmentExtension
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ItemStack

object AerodynamicsPactEnchantment : Enchantment(Rarity.RARE, EnchantmentTarget.BOW, arrayOf(EquipmentSlot.MAINHAND)), EnchantmentExtension {

    const val ENCHANTMENT_MAX_PULL_TIME = 40f

    override fun getMinPower(level: Int) = 25
    override fun getMaxPower(level: Int) = 50
    override fun getMaxLevel() = 1

    override fun isTreasure() = true
    override fun isAvailableForEnchantedBookOffer() = false
    override fun isPact() = true

    fun getPullProgress(item: ItemStack, useTicks: Int): Float {
        val maxPullTime = if (EnchantmentHelper.getLevel(this, item) > 0) ENCHANTMENT_MAX_PULL_TIME else 20f
        var f = useTicks.toFloat() / maxPullTime
        f = (f * f + f * 2f) / 3f
        if (f > 1f)
            f = 1f
        return f
    }
}