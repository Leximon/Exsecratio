package de.leximon.exsecratio.mixin.interfaces;

public interface EnchantmentExtension {

    default boolean isPact() {
        return false;
    }

}
