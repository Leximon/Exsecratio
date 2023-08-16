package de.leximon.exsecratio.mixin.classes.enchantment.reflection_curse;

import de.leximon.exsecratio.common.enchantments.ReflectionCurseEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getFireAspect(Lnet/minecraft/entity/LivingEntity;)I"))
    private void injectReflectionCurse(Entity target, CallbackInfo ci) {
        ItemStack itemStack = this.getEquippedStack(EquipmentSlot.MAINHAND);
        if (EnchantmentHelper.getEquipmentLevel(ReflectionCurseEnchantment.INSTANCE, this) > 0)
            ReflectionCurseEnchantment.INSTANCE.applyTo(this, target, itemStack);
    }

}
