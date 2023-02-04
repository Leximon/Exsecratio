package de.leximon.exsecratio.mixin.extensions;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.util.Pair;

import java.util.Map;
import java.util.UUID;

public interface EnchantmentExtension {

    default boolean isPact() {
        return false;
    }

    default Map<EntityAttribute, Pair<UUID, Double>> getAttributes(int level) {
        return ImmutableMap.of();
    }

}
