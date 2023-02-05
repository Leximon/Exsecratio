package de.leximon.exsecratio

import de.leximon.exsecratio.common.ModParticles
import de.leximon.exsecratio.common.components.AccelerationStreakComponent
import ladysnake.satin.api.event.ShaderEffectRenderCallback
import ladysnake.satin.api.managed.ShaderEffectManager
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.util.math.MathHelper

object ExsecratioClient : ClientModInitializer {
    private const val STREAK_CHANGE_RATE = 0.05f
    private val SHADER_ACCELERATION_STREAK = ShaderEffectManager.getInstance().manage("shaders/post/acceleration_streak.json".id())

    private var prevLerpedStreak = 0f
    private var lerpedStreak = 0f

    override fun onInitializeClient() {
        ModParticles.initClient()

        ShaderEffectRenderCallback.EVENT.register { tickDelta ->
            val streakValue = MathHelper.lerp(tickDelta, prevLerpedStreak, lerpedStreak) / AccelerationStreakComponent.MAX_STREAK.toFloat()
            if (streakValue > 0f) {
                SHADER_ACCELERATION_STREAK.setUniformValue("Strength", streakValue)
                SHADER_ACCELERATION_STREAK.render(tickDelta)
            }
        }
        ClientTickEvents.START_CLIENT_TICK.register { client ->
            val player = client.player ?: return@register
            val streak = ExsecratioComponents.ACCELERATION_STREAK.get(player).streak
            prevLerpedStreak = lerpedStreak
            if (streak > lerpedStreak) {
                lerpedStreak += STREAK_CHANGE_RATE
                if (streak < lerpedStreak)
                    lerpedStreak = streak.toFloat()
            } else if (streak < lerpedStreak) {
                lerpedStreak -= STREAK_CHANGE_RATE
                if (streak > lerpedStreak)
                    lerpedStreak = streak.toFloat()
            }
        }
    }

}