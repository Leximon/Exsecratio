package de.leximon.exsecratio;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import de.leximon.exsecratio.enchantments.DecayCurseEnchantment;
import de.leximon.exsecratio.enchantments.BloodPactEnchantment;
import de.leximon.exsecratio.mixin.classes.EnchantmentAccessor;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class Exsecratio implements ModInitializer {

	public static final String MOD_ID = "exsecratio";
	public static final Logger LOGGER = LoggerFactory.getLogger("Exsecratio");

	public static final Enchantment DECAY_CURSE = registerEnchantment("decay_curse", new DecayCurseEnchantment());
	public static final Enchantment BLOOD_PACT = registerEnchantment("blood_pact", new BloodPactEnchantment());

	@Override
	public void onInitialize() {
		ModifyItemAttributeModifiersCallback.EVENT.register(this::onModifyItemAttributeModifiers);
	}

	private void onModifyItemAttributeModifiers(ItemStack item, EquipmentSlot slot, Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers) {
		// merge all enchantment values
		Map<EntityAttribute, Pair<UUID, Double>> enchantmentAttributes = Maps.newHashMap();
		for (Map.Entry<Enchantment, Integer> entry : EnchantmentHelper.get(item).entrySet()) {
			Enchantment enchantment = entry.getKey();
			EquipmentSlot[] slots = ((EnchantmentAccessor) enchantment).exsecratio$getSlotTypes();
			if (slots.length == 0 || Arrays.stream(slots).noneMatch(slot::equals))
				continue;
			for (Map.Entry<EntityAttribute, Pair<UUID, Double>> entry2 : enchantment.getAttributes(entry.getValue()).entrySet()) {
				enchantmentAttributes.compute(
						entry2.getKey(),
						(attribute, value) -> {
							if (value == null)
								return entry2.getValue();
							value.setRight(value.getRight() + entry2.getValue().getRight());
							return value;
						}
				);
			}
		}

		ArrayList<Pair<EntityAttribute, EntityAttributeModifier>> toAdd = new ArrayList<>();
		attributeModifiers.forEach((attribute, modifier) -> toAdd.add(new Pair<>(attribute, modifier)));

		for (Map.Entry<EntityAttribute, Pair<UUID, Double>> entry : enchantmentAttributes.entrySet()) {
			EntityAttributeModifier modifier = attributeModifiers.get(entry.getKey()).stream().findFirst().orElse(null);
			if (modifier == null) {
				// create a new modifier
				Pair<UUID, Double> value = entry.getValue();
				EntityAttributeModifier newModifier = new EntityAttributeModifier(value.getLeft(), "Pact Enchantment", value.getRight(), EntityAttributeModifier.Operation.ADDITION);
				toAdd.add(new Pair<>(entry.getKey(), newModifier));
				continue;
			}
			// copy the modifier and add the enchantment value
			EntityAttributeModifier newModifier = new EntityAttributeModifier(modifier.getId(), modifier.getName(), modifier.getValue() + entry.getValue().getRight(), modifier.getOperation());
			int i = 0;
			for (Pair<EntityAttribute, EntityAttributeModifier> pair : toAdd) {
				if (pair.getLeft() == entry.getKey() && pair.getRight() == modifier) {
					toAdd.set(i, new Pair<>(entry.getKey(), newModifier));
					break;
				}
				i++;
			}
		}

		// re-add all modifiers to keep the order
		attributeModifiers.clear();
		for (Pair<EntityAttribute, EntityAttributeModifier> pair : toAdd)
			attributeModifiers.put(pair.getLeft(), pair.getRight());
	}

	private static Enchantment registerEnchantment(String name, Enchantment enchantment) {
		return Registry.register(Registry.ENCHANTMENT, id(name), enchantment);
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}