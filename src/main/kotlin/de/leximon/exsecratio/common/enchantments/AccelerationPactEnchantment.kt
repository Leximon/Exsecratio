package de.leximon.exsecratio.common.enchantments

import de.leximon.exsecratio.mixin.extensions.EnchantmentExtension
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.AxeItem
import net.minecraft.item.ItemStack

object AccelerationPactEnchantment : Enchantment(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, arrayOf(EquipmentSlot.MAINHAND)), EnchantmentExtension {
    override fun getMinPower(level: Int) = 25
    override fun getMaxPower(level: Int) = 50
    override fun getMaxLevel() = 1

    override fun isTreasure() = true
    override fun isAvailableForEnchantedBookOffer() = false
    override fun isPact() = true

    override fun isAcceptableItem(stack: ItemStack) = if (stack.item is AxeItem) true else super.isAcceptableItem(stack)

    fun shouldHandleEnchantment(player: PlayerEntity, item: ItemStack = player.mainHandStack) = EnchantmentHelper.getLevel(this, item) > 0
}