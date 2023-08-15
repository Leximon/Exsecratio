package de.leximon.exsecratio.mixin.classes.enchantment.aerodynamics_pact;

import de.leximon.exsecratio.common.enchantments.AerodynamicsPactEnchantment;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ModelPredicateProviderRegistry.class)
public class ModelPredicateProviderRegistryMixin {

    @ModifyConstant(method = "method_27890", constant = @Constant(floatValue = 20.0F))
    private static float modifyMaxPullProgress(float value, ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity entity, int seed) {
        return EnchantmentHelper.getLevel(AerodynamicsPactEnchantment.INSTANCE, stack) > 0
                ? AerodynamicsPactEnchantment.ENCHANTMENT_MAX_PULL_TIME
                : value;
    }

}
