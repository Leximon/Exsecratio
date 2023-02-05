package de.leximon.exsecratio

import de.leximon.exsecratio.mixin.classes.EnchantmentAccessor
import dev.onyxstudios.cca.api.v3.component.Component
import dev.onyxstudios.cca.api.v3.component.ComponentFactory
import dev.onyxstudios.cca.api.v3.component.ComponentKey
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import net.minecraft.util.Pair as McPair

const val MOD_ID = "exsecratio"
val LOGGER = LoggerFactory.getLogger("Exsecratio") as Logger

fun String.id() = Identifier(MOD_ID, this)

inline fun <reified C : Component> ComponentRegistryV3.getOrCreate(name: String): ComponentKey<C> = getOrCreate(name.id(), C::class.java)

infix fun <A, B> A.to(that: B) = McPair(this, that)

fun Enchantment.getSlotTypes(): Array<EquipmentSlot> = (this as EnchantmentAccessor).exsecratio_getSlotTypes()