package de.leximon.exsecratio.common.components

import de.leximon.exsecratio.ExsecratioComponents
import de.leximon.exsecratio.LOGGER
import de.leximon.exsecratio.common.sendAccelerationStreakReset
import dev.onyxstudios.cca.api.v3.component.ComponentProvider
import dev.onyxstudios.cca.api.v3.component.ComponentV3
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.network.ServerPlayerEntity
import java.util.*

@Suppress("UnstableApiUsage")
class AccelerationStreakComponent(private val player: PlayerEntity) : ComponentV3, ServerTickingComponent, AutoSyncedComponent {
    companion object {
        private val MODIFIER_UUID = UUID.fromString("e1704372-3e37-4efc-bcc6-943e05e1c0ef")
        private const val MAX_STREAK = 5.0
        private const val STREAK_DECAY_TIME = 60 // 2 seconds
    }

    private var streak = 0
    private var resetTicks = 0 // the amount of ticks since the last hit (or -1 if no hit was made)
    private var punishment = 0
    private var punishmentRemainingTicks = 0 // the amount of remaining ticks the punishment is active

    fun hit() {
        if (player.getAttackCooldownProgress(0.5f) < 1f) {
            miss(true)
            return
        }

        if (streak < MAX_STREAK)
            streak++
        resetTicks = 0
        updateAttackSpeed()
        if (!player.world.isClient) // sync to client if run on server
            syncToClient()
    }

    /**
     * Misses a hit and resets the streak.
     * @param sync Whether to sync client to server or server to client
     */
    fun miss(sync: Boolean, punish: Boolean = false) {
        if (punish)
            punishment = streak
        streak = 0
        resetTicks = -1
        updateAttackSpeed()
        if (punish)
            punishmentRemainingTicks = player.attackCooldownProgressPerTick.toInt() // name is misleading, it's actually the total amount of ticks for the attack cooldown

        if (!sync)
            return
        if (player.world.isClient)
            sendAccelerationStreakReset(punish) // sync streak reset to server
        else syncToClient()
    }

    override fun serverTick() {
        if (resetTicks != -1) {
            resetTicks++
            if (resetTicks >= STREAK_DECAY_TIME)
                miss(true)
        }
        if (punishment > 0) {
            if (punishmentRemainingTicks <= 0) {
                punishment = 0
                updateAttackSpeed()
            }
            punishmentRemainingTicks--
        }
    }

    override fun readFromNbt(tag: NbtCompound) {
        streak = tag.getInt("streak")
        resetTicks = tag.getInt("resetTicks")
        punishment = tag.getInt("punishment")
        updateAttackSpeed()
    }

    override fun writeToNbt(tag: NbtCompound) {
        tag.putInt("streak", streak)
        tag.putInt("resetTicks", resetTicks)
        tag.putInt("punishment", punishment)
    }

    private fun updateAttackSpeed() {
        if (player.attributes == null)
            return
        val value = if (punishment > 0)
            punishment / MAX_STREAK * -0.75
        else streak / MAX_STREAK * 0.65

        val instance = player.attributes.getCustomInstance(EntityAttributes.GENERIC_ATTACK_SPEED)!!
        instance.removeModifier(MODIFIER_UUID)
        instance.addTemporaryModifier(EntityAttributeModifier(
            MODIFIER_UUID, "Acceleration Streak",
            value, EntityAttributeModifier.Operation.MULTIPLY_TOTAL
        ))
    }

    /**
     * Syncs the component with the component provider (player)
     */
    private fun syncToClient() {
        if (player.world.isClient)
            throw IllegalStateException("Cannot sync client to server")
        ExsecratioComponents.ACCELERATION_STREAK.syncWith(player as ServerPlayerEntity, player as ComponentProvider)
    }
}