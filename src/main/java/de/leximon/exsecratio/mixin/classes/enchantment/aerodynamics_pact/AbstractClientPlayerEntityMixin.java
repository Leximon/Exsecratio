package de.leximon.exsecratio.mixin.classes.enchantment.aerodynamics_pact;

import de.leximon.exsecratio.common.enchantments.AerodynamicsPactEnchantment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AbstractClientPlayerEntity.class)
public class AbstractClientPlayerEntityMixin {

    @ModifyConstant(method = "getFovMultiplier", constant = @Constant(floatValue = 20.0f))
    private float modifyFovMultiplier(float value) {
        ItemStack stack = ((AbstractClientPlayerEntity) (Object) this).getActiveItem();
        return EnchantmentHelper.getLevel(AerodynamicsPactEnchantment.INSTANCE, stack) > 0
                ? AerodynamicsPactEnchantment.ENCHANTMENT_MAX_PULL_TIME
                : value;
    }

}
