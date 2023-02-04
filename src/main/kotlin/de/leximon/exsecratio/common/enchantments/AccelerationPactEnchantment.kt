package de.leximon.exsecratio.common.enchantments

import de.leximon.exsecratio.mixin.extensions.EnchantmentExtension
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.AxeItem
import net.minecraft.item.ItemStack
import net.minecraft.util.Pair
import java.util.*

object AccelerationPactEnchantment : Enchantment(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, arrayOf(EquipmentSlot.MAINHAND)), EnchantmentExtension {

    private val ATTACK_SPEED_UUID = UUID.fromString("13139de4-34a6-429c-bed2-be71f2529620")

    override fun getMinPower(level: Int) = 25
    override fun getMaxPower(level: Int) = 50
    override fun getMaxLevel() = 1

    override fun isAcceptableItem(stack: ItemStack) = stack.item is AxeItem || super.isAcceptableItem(stack)
    override fun isTreasure() = true
    override fun isAvailableForEnchantedBookOffer() = false
    override fun isPact() = true
    override fun getAttributes(level: Int) = mapOf(
        EntityAttributes.GENERIC_ATTACK_SPEED to Pair(ATTACK_SPEED_UUID, -0.1)
    )

    fun shouldHandleEnchantment(player: PlayerEntity, item: ItemStack = player.mainHandStack) = EnchantmentHelper.getLevel(this, item) > 0
}