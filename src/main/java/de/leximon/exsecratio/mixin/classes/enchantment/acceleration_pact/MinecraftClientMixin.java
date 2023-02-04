package de.leximon.exsecratio.mixin.classes.enchantment.acceleration_pact;

import de.leximon.exsecratio.ExsecratioComponents;
import de.leximon.exsecratio.common.enchantments.AccelerationPactEnchantment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow @Nullable public ClientPlayerEntity player;

    @Inject(method = "doAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;attackEntity(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/entity/Entity;)V"))
    private void injectHit(CallbackInfoReturnable<Boolean> cir) {
        if (AccelerationPactEnchantment.INSTANCE.shouldHandleEnchantment(player, player.getMainHandStack()))
            ExsecratioComponents.INSTANCE.getACCELERATION_STREAK().get(player).hit();
    }

    @Inject(method = "doAttack", at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/util/hit/BlockHitResult;getBlockPos()Lnet/minecraft/util/math/BlockPos;", ordinal = 0),
            @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;hasLimitedAttackSpeed()Z", ordinal = 1)
    })
    private void injectMiss(CallbackInfoReturnable<Boolean> cir) {
        if (AccelerationPactEnchantment.INSTANCE.shouldHandleEnchantment(player, player.getMainHandStack()))
            ExsecratioComponents.INSTANCE.getACCELERATION_STREAK().get(player).miss(true);
    }

}
