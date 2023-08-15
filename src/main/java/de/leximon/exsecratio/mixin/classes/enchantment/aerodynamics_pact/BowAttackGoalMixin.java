package de.leximon.exsecratio.mixin.classes.enchantment.aerodynamics_pact;

import de.leximon.exsecratio.common.enchantments.AerodynamicsPactEnchantment;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BowAttackGoal.class)
public class BowAttackGoalMixin<T extends HostileEntity & RangedAttackMob> {

    @Shadow @Final private T actor;

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BowItem;getPullProgress(I)F"))
    private float modifyPullProgress(int useTicks) {
        ItemStack stack = this.actor.getActiveItem();
        return AerodynamicsPactEnchantment.INSTANCE.getPullProgress(stack, useTicks);
    }

}
