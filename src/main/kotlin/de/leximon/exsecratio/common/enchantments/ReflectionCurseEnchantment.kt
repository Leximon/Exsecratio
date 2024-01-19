package de.leximon.exsecratio.common.enchantments

import de.leximon.exsecratio.common.ModDamageSources
import de.leximon.exsecratio.common.ModSounds
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack

object ReflectionCurseEnchantment : Enchantment(Rarity.RARE, EnchantmentTarget.WEAPON, EquipmentSlot.values()) {

    override fun getMinPower(level: Int) = 25
    override fun getMaxPower(level: Int) = 50
    override fun getMaxLevel() = 1

    override fun isTreasure() = true
    override fun isCursed() = true

    fun applyTo(entity: LivingEntity, target: Entity) {
        entity.damage(entity.world.damageSources.create(ModDamageSources.REFLECTED, entity, target), 1f)
        entity.playSound(ModSounds.REFLECTION_HIT, 1f, 1f);
    }
}
