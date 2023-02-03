package de.leximon.exsecratio.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;

public class GorePactEnchantment extends Enchantment {

    public GorePactEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[] { EquipmentSlot.CHEST });
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        return 1;
    }

    @Override
    public boolean isPact() {
        return true;
    }
}
