package de.leximon.exsecratio.mixin.classes;

import de.leximon.exsecratio.mixin.extensions.EnchantmentExtension;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Enchantment.class)
public class EnchantmentMixin implements EnchantmentExtension {

    @Inject(method = "getName", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;", ordinal = 1, shift = At.Shift.BY, by = 2), locals = LocalCapture.CAPTURE_FAILHARD)
    private void injectPactName(int level, CallbackInfoReturnable<Text> cir, MutableText mutableText) {
        if (this.isPact())
            mutableText.styled(style -> style.withColor(0xff9955));
    }

}
