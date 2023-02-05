package de.leximon.exsecratio.mixin.classes.enchantment.aerodynamics_pact;

import de.leximon.exsecratio.ExsecratioComponents;
import de.leximon.exsecratio.common.ModDamageSources;
import de.leximon.exsecratio.common.ModParticles;
import de.leximon.exsecratio.common.components.AerodynamicShotComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin extends ProjectileEntity {

    @Shadow protected boolean inGround;

    public PersistentProjectileEntityMixin(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "onBlockHit", at = @At("HEAD"))
    private void injectHit(BlockHitResult blockHitResult, CallbackInfo ci) {
        AerodynamicShotComponent component = ExsecratioComponents.INSTANCE.getAERODYNAMIC_SHOT().getNullable(this);
        if (component != null && component.isAerodynamicShot()) {
            Entity owner = this.getOwner();
            if (owner != null)
                owner.damage(ModDamageSources.INSTANCE.getSHOT_MISSED(), 2.0F);
            component.setAerodynamicShot(false);
        }
    }
//
//    @Inject(method = "tick", at = @At("TAIL"))
//    private void injectParticles(CallbackInfo ci) {
//        if (inGround)
//            return;
//        world.addImportantParticle(ModParticles.INSTANCE.getAIR_FLOW(), true, getX(), getY(), getZ(), 0, 0, 0);
//    }

}
