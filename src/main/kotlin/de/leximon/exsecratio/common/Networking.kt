package de.leximon.exsecratio.common

import de.leximon.exsecratio.ExsecratioComponents
import de.leximon.exsecratio.id
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.player.PlayerEntity

private val RESET_ACCELERATION_STREAK = "reset_acceleration_streak".id()

fun initServerPacketHandlers() {
    ServerPlayNetworking.registerGlobalReceiver(RESET_ACCELERATION_STREAK) { _, player, _, _, _ ->
        ExsecratioComponents.ACCELERATION_STREAK.get(player).miss(false)
    }
}

@Environment(EnvType.CLIENT)
fun sendAccelerationStreakReset() {
    ClientPlayNetworking.send(RESET_ACCELERATION_STREAK, PacketByteBufs.empty())
}
