package de.leximon.exsecratio.mixin.enchantment;

import de.leximon.exsecratio.Exsecratio;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {

	@ModifyVariable(
			method = "damage(ILnet/minecraft/util/math/random/Random;Lnet/minecraft/server/network/ServerPlayerEntity;)Z",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getLevel(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/item/ItemStack;)I"),
			argsOnly = true,
			ordinal = 0
	)
	private int injectDecayCurse(int amount) {
		int decayCurseLevel = EnchantmentHelper.getLevel(Exsecratio.ENCHANTMENT_DECAY_CURSE, (ItemStack) (Object) this);
		if (decayCurseLevel > 0)
			return amount * 3;
		return amount;
	}

}