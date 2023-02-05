package de.leximon.exsecratio.mixin.classes.enchantment.aerodynamics_pact;

import de.leximon.exsecratio.ExsecratioComponents;
import de.leximon.exsecratio.common.components.AerodynamicShotComponent;
import de.leximon.exsecratio.common.enchantments.AerodynamicsPactEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BowItem.class)
public class BowItemMixin {

    @ModifyVariable(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;setVelocity(Lnet/minecraft/entity/Entity;FFFFF)V"), ordinal = 0)
    private PersistentProjectileEntity modifyEntity(PersistentProjectileEntity entity, ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (EnchantmentHelper.getLevel(AerodynamicsPactEnchantment.INSTANCE, stack) > 0) {
            AerodynamicShotComponent component = ExsecratioComponents.INSTANCE.getAERODYNAMIC_SHOT().get(entity);
            component.setAerodynamicShot(true);
        }
        return entity;
    }

    @ModifyVariable(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ArrowItem;createArrow(Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/LivingEntity;)Lnet/minecraft/entity/projectile/PersistentProjectileEntity;"), ordinal = 0)
    private float modifyBowVelocity(float speed, ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (EnchantmentHelper.getLevel(AerodynamicsPactEnchantment.INSTANCE, stack) > 0) {
            return speed * 1.5F;
        }
        return speed;
    }


}
