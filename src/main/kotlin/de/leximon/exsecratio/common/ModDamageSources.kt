package de.leximon.exsecratio.common

import net.minecraft.entity.damage.DamageSource

object ModDamageSources {

    val SHOT_MISSED: DamageSource = DamageSource("shot_missed")
        .setBypassesArmor()
        .setBypassesProtection()

    fun init() {}
}