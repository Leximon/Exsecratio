package de.leximon.exsecratio.mixin.classes.enchantment.acceleration_pact;

import de.leximon.exsecratio.common.enchantments.AccelerationPactEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {


    @Shadow public abstract ItemStack getMainHandStack();

    @ModifyVariable(method = "takeKnockback", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private double modifyKnockback(double d) {
        if (EnchantmentHelper.getLevel(AccelerationPactEnchantment.INSTANCE, getMainHandStack()) > 0)
            return d * 2;
        return d;
    }

}
