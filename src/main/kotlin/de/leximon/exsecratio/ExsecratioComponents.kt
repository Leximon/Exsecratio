package de.leximon.exsecratio

import de.leximon.exsecratio.common.components.AccelerationStreakComponent
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy

object ExsecratioComponents : EntityComponentInitializer {

    val ACCELERATION_STREAK = ComponentRegistryV3.INSTANCE.getOrCreate<AccelerationStreakComponent>("acceleration_streak")

    override fun registerEntityComponentFactories(registry: EntityComponentFactoryRegistry) {
        registry.registerForPlayers(ACCELERATION_STREAK, ::AccelerationStreakComponent, RespawnCopyStrategy.ALWAYS_COPY)
    }

}