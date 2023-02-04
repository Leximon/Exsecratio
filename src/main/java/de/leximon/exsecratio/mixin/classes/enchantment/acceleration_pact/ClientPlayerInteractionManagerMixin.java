package de.leximon.exsecratio.mixin.classes.enchantment.acceleration_pact;

import de.leximon.exsecratio.ExsecratioComponents;
import de.leximon.exsecratio.common.enchantments.AccelerationPactEnchantment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {

    @Shadow @Final private MinecraftClient client;

    @Shadow private int lastSelectedSlot;

    @Inject(method = "syncSelectedSlot", at = @At(value = "FIELD", target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;lastSelectedSlot:I", ordinal = 1))
    private void injectMissByDeselect(CallbackInfo ci) {
        if (AccelerationPactEnchantment.INSTANCE.shouldHandleEnchantment(client.player, client.player.getInventory().getStack(lastSelectedSlot)))
            ExsecratioComponents.INSTANCE.getACCELERATION_STREAK().get(client.player).miss(false);
    }

}
