package de.leximon.exsecratio.mixin.classes;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Enchantment.class)
public interface EnchantmentAccessor {

    @Accessor("slotTypes")
    EquipmentSlot[] exsecratio$getSlotTypes();

}
