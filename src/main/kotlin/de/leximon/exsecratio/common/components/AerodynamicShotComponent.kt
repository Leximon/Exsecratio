package de.leximon.exsecratio.common.components

import de.leximon.exsecratio.common.ModParticles
import dev.onyxstudios.cca.api.v3.component.ComponentV3
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent
import dev.onyxstudios.cca.api.v3.component.tick.ClientTickingComponent
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.projectile.ArrowEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.math.Vec3d

class AerodynamicShotComponent(val arrow: ArrowEntity) : ComponentV3, ClientTickingComponent, AutoSyncedComponent {

    var isAerodynamicShot = false

    override fun clientTick() {
        if (isAerodynamicShot) {
            val world = arrow.world as ClientWorld
            val dir = Vec3d(arrow.x - arrow.prevX, arrow.y - arrow.prevY, arrow.z - arrow.prevZ)
            val particleVel = dir.multiply(-0.2)
            val pos = dir.multiply(2.0).add(arrow.x, arrow.y, arrow.z)
            world.addImportantParticle(
                ModParticles.AIR_FLOW, true,
                pos.x, pos.y, pos.z,
                particleVel.x, particleVel.y, particleVel.z
            )
        }
    }

    override fun writeToNbt(tag: NbtCompound) {
        tag.putBoolean("isAerodynamicShot", isAerodynamicShot)
    }

    override fun readFromNbt(tag: NbtCompound) {
        isAerodynamicShot = tag.getBoolean("isAerodynamicShot")
    }
}