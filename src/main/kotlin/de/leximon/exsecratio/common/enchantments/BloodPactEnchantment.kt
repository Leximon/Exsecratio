package de.leximon.exsecratio.common.enchantments

import com.google.common.collect.ImmutableMap
import de.leximon.exsecratio.mixin.extensions.EnchantmentExtension
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Pair
import java.util.*

object BloodPactEnchantment : Enchantment(Rarity.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, arrayOf(EquipmentSlot.CHEST)), EnchantmentExtension {

    private val ATTACK_DAMAGE_UUID = UUID.fromString("6a91c483-0c1e-41ed-b518-09aa349cb088")
    private val MAX_HEALTH_UUID = UUID.fromString("cbf5078f-1094-4212-bc34-f1d958054c91")

    override fun getMinPower(level: Int) = 25
    override fun getMaxPower(level: Int) = 50
    override fun getMaxLevel() = 1

    override fun isTreasure() = true
    override fun isAvailableForEnchantedBookOffer() = false
    override fun isPact() = true
    override fun getAttributes(level: Int) = mapOf(
        EntityAttributes.GENERIC_ATTACK_DAMAGE to Pair(ATTACK_DAMAGE_UUID, 2.0),
        EntityAttributes.GENERIC_MAX_HEALTH to Pair(MAX_HEALTH_UUID, -4.0)
    )
}