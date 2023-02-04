package de.leximon.exsecratio.mixin.classes.enchantment.acceleration_pact;

import de.leximon.exsecratio.ExsecratioComponents;
import de.leximon.exsecratio.common.enchantments.AccelerationPactEnchantment;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {

    @Shadow public ServerPlayerEntity player;

    @Inject(method = "onUpdateSelectedSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;getHotbarSize()I"))
    private void injectMissByDeselect(UpdateSelectedSlotC2SPacket packet, CallbackInfo ci) {
        if (AccelerationPactEnchantment.INSTANCE.shouldHandleEnchantment(player, player.getMainHandStack()))
            ExsecratioComponents.INSTANCE.getACCELERATION_STREAK().get(player).miss(false, false);
    }

}
