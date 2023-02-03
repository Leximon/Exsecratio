package de.leximon.exsecratio.enchantments;

import com.google.common.collect.ImmutableMap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Pair;

import java.util.Map;
import java.util.UUID;

public class BloodPactEnchantment extends Enchantment {

    private static final UUID ATTACK_DAMAGE_UUID = UUID.fromString("6a91c483-0c1e-41ed-b518-09aa349cb088");
    private static final UUID MAX_HEALTH_UUID = UUID.fromString("cbf5078f-1094-4212-bc34-f1d958054c91");

    public BloodPactEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[] { EquipmentSlot.CHEST });
    }

    @Override
    public boolean isPact() {
        return true;
    }

    @Override
    public int getMinPower(int level) {
        return 25;
    }

    @Override
    public int getMaxPower(int level) {
        return 50;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    @Override
    public Map<EntityAttribute, Pair<UUID, Double>> getAttributes(int level) {
        return ImmutableMap.of(
                EntityAttributes.GENERIC_ATTACK_DAMAGE, new Pair<>(ATTACK_DAMAGE_UUID, 2.0),
                EntityAttributes.GENERIC_MAX_HEALTH, new Pair<>(MAX_HEALTH_UUID, -2.0)
        );
    }
}
