package de.leximon.exsecratio

import com.google.common.collect.Maps
import com.google.common.collect.Multimap
import de.leximon.exsecratio.common.enchantments.AccelerationPactEnchantment
import de.leximon.exsecratio.common.enchantments.BloodPactEnchantment
import de.leximon.exsecratio.common.enchantments.DecayCurseEnchantment
import de.leximon.exsecratio.common.initServerPacketHandlers
import de.leximon.exsecratio.mixin.extensions.EnchantmentExtension
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback
import net.minecraft.enchantment.DamageEnchantment
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.ActionResult
import net.minecraft.util.Pair
import java.util.UUID

object Exsecratio : ModInitializer {

	val DECAY_CURSE = registerEnchantment("decay_curse", DecayCurseEnchantment)
	val BLOOD_PACT = registerEnchantment("blood_pact", BloodPactEnchantment)
	val ACCELERATION_PACT = registerEnchantment("acceleration_pact", AccelerationPactEnchantment)

	override fun onInitialize() {
		initServerPacketHandlers()
		ModifyItemAttributeModifiersCallback.EVENT.register(this::onModifyItemAttributeModifiers)
		LOGGER.info("Exsecratio has been initialized! :D")
	}

	private fun onModifyItemAttributeModifiers(
		item: ItemStack,
		slot: EquipmentSlot,
		attributeModifiers: Multimap<EntityAttribute, EntityAttributeModifier>
	) {
		val enchantmentAttributes = Maps.newHashMap<EntityAttribute, Pair<UUID, Double>>()
		for ((enchantment, level) in EnchantmentHelper.get(item)) {
			val slots = enchantment.getSlotTypes()
			if (slots.isEmpty() || !slots.contains(slot))
				continue
			for ((attribute, value) in (enchantment as EnchantmentExtension).getAttributes(level))
				enchantmentAttributes.compute(attribute) { _, v -> v?.apply { right += value.right } ?: value }
		}

		val toAdd = mutableListOf<Pair<EntityAttribute, EntityAttributeModifier>>()
		attributeModifiers.entries().mapTo(toAdd) { it.key to it.value }

		for ((attribute, value) in enchantmentAttributes) {
			val modifier = attributeModifiers.get(attribute).firstOrNull()
			if (modifier == null) {
				// create new modifier
				val newModifier = EntityAttributeModifier(value.left, "Pact Enchantment", value.right, EntityAttributeModifier.Operation.ADDITION)
				toAdd.add(attribute to newModifier)
				continue
			}

			// copy the modifier and add the enchantment value
			val newModifier = EntityAttributeModifier(modifier.id, modifier.name, modifier.value + value.right, modifier.operation)
			toAdd.withIndex().find { it.value.left == attribute && it.value.right == modifier }?.apply {
				toAdd[index] = attribute to newModifier
			}
		}

		// re-add all modifiers to keep the order
		attributeModifiers.clear()
		toAdd.forEach { attributeModifiers.put(it.left, it.right) }
	}

	private fun registerEnchantment(
		name: String,
		enchantment: Enchantment
	) = Registry.register(Registries.ENCHANTMENT, name.id(), enchantment)
}