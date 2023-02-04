package de.leximon.exsecratio.common.components

import de.leximon.exsecratio.common.sendAccelerationStreakReset
import dev.onyxstudios.cca.api.v3.component.ComponentV3
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import java.util.*
import kotlin.math.min

class AccelerationStreakComponent(private val player: PlayerEntity) : ComponentV3 {
    companion object {
        private val MODIFIER_UUID = UUID.fromString("e1704372-3e37-4efc-bcc6-943e05e1c0ef")
    }

    private var streak = 0

    fun hit() {
        streak++
        updateAttackSpeed()
    }

    fun miss(sync: Boolean) {
        streak = 0
        if (player.world.isClient && sync)
            sendAccelerationStreakReset()
        updateAttackSpeed()
    }

    private fun updateAttackSpeed() {
        val instance = player.attributes.getCustomInstance(EntityAttributes.GENERIC_ATTACK_SPEED)!!
        instance.removeModifier(MODIFIER_UUID)
        instance.addTemporaryModifier(EntityAttributeModifier(
            MODIFIER_UUID,
            "Acceleration Streak",
            min(streak / 5.0, 1.0) * 0.5,
            EntityAttributeModifier.Operation.ADDITION
        ))
    }

    override fun readFromNbt(tag: NbtCompound) {
        tag.putInt("streak", streak)
    }

    override fun writeToNbt(tag: NbtCompound) {
        streak = tag.getInt("streak")
    }
}