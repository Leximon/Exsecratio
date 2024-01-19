package de.leximon.exsecratio.common

import de.leximon.exsecratio.Exsecratio
import de.leximon.exsecratio.id
import net.minecraft.entity.damage.DamageType
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

object ModDamageSources {

    val SHOT_MISSED: RegistryKey<DamageType> = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, "shot_missed".id())
    val REFLECTED: RegistryKey<DamageType> = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, "reflected".id())

//    val SHOT_MISSED: DamageSource = DamageSource("shot_missed")
//        .setBypassesArmor()
//        .setBypassesProtection()
//
//    class ReflectedDamageSource(val target: Entity, val weapon: ItemStack) : DamageSource("reflected") {
//        init {
//            setBypassesArmor()
//            setBypassesProtection()
//        }
//
//        override fun getDeathMessage(entity: LivingEntity): Text {
//            return if (!weapon.isEmpty && weapon.hasCustomName())
//                Text.translatable("death.attack.$name.item", entity.displayName, target.displayName, weapon)
//            else
//                Text.translatable("death.attack.$name", entity.displayName, target.displayName)
//        }
//    }

    fun init() {}
}