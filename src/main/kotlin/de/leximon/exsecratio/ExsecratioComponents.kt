package de.leximon.exsecratio

import de.leximon.exsecratio.common.components.AccelerationStreakComponent
import de.leximon.exsecratio.common.components.AerodynamicShotComponent
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy
import net.minecraft.entity.projectile.ArrowEntity

object ExsecratioComponents : EntityComponentInitializer {

    val ACCELERATION_STREAK = ComponentRegistryV3.INSTANCE.getOrCreate<AccelerationStreakComponent>("acceleration_streak")
    val AERODYNAMIC_SHOT = ComponentRegistryV3.INSTANCE.getOrCreate<AerodynamicShotComponent>("aerodynamic_shot")

    override fun registerEntityComponentFactories(registry: EntityComponentFactoryRegistry) {
        registry.registerForPlayers(ACCELERATION_STREAK, ::AccelerationStreakComponent, RespawnCopyStrategy.ALWAYS_COPY)
        registry.registerFor(ArrowEntity::class.java, AERODYNAMIC_SHOT, ::AerodynamicShotComponent)
    }

}