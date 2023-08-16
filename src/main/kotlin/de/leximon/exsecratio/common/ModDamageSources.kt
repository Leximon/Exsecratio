package de.leximon.exsecratio.common

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.ItemStack
import net.minecraft.text.Text

object ModDamageSources {

    val SHOT_MISSED: DamageSource = DamageSource("shot_missed")
        .setBypassesArmor()
        .setBypassesProtection()

    class ReflectedDamageSource(val target: Entity, val weapon: ItemStack) : DamageSource("reflected") {
        init {
            setBypassesArmor()
            setBypassesProtection()
        }

        override fun getDeathMessage(entity: LivingEntity): Text {
            return if (!weapon.isEmpty && weapon.hasCustomName())
                Text.translatable("death.attack.$name.item", entity.displayName, target.displayName, weapon)
            else
                Text.translatable("death.attack.$name", entity.displayName, target.displayName)
        }
    }

    fun init() {}
}