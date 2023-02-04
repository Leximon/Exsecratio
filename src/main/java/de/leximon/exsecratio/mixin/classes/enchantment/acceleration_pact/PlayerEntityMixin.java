package de.leximon.exsecratio.mixin.classes.enchantment.acceleration_pact;

import de.leximon.exsecratio.ExsecratioComponents;
import de.leximon.exsecratio.common.enchantments.AccelerationPactEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;onTargetDamaged(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/Entity;)V"))
    private void injectHit(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (AccelerationPactEnchantment.INSTANCE.shouldHandleEnchantment(player, player.getMainHandStack()))
            ExsecratioComponents.INSTANCE.getACCELERATION_STREAK().get(this).hit();
    }

}
