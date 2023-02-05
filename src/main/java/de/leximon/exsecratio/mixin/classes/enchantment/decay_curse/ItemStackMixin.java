package de.leximon.exsecratio.mixin.classes.enchantment.decay_curse;

import de.leximon.exsecratio.common.enchantments.DecayCurseEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemStack.class)
public class ItemStackMixin {

	@ModifyVariable(
			method = "damage(ILnet/minecraft/util/math/random/Random;Lnet/minecraft/server/network/ServerPlayerEntity;)Z",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getLevel(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/item/ItemStack;)I"),
			argsOnly = true,
			ordinal = 0
	)
	private int injectDecayCurse(int amount) {
		int decayCurseLevel = EnchantmentHelper.getLevel(DecayCurseEnchantment.INSTANCE, (ItemStack) (Object) this);
		if (decayCurseLevel > 0)
			return amount * 3;
		return amount;
	}

}