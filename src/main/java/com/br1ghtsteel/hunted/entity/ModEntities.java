package com.br1ghtsteel.hunted.entity;

import com.br1ghtsteel.hunted.Hunted;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<GhoulEntity> GHOUL_ENTITY = registerEntity("ghoul",
            EntityType.Builder.create(GhoulEntity::new, SpawnGroup.MONSTER)
                    .setDimensions(0.6F, 1.95F)
                    .maxTrackingRange(8)
    );

    public static final EntityType<LurcherEntity> LURCHER_ENTITY = registerEntity("lurcher",
            EntityType.Builder.create(LurcherEntity::new, SpawnGroup.MONSTER)
                    .setDimensions(0.6F, 1.7F)
                    .maxTrackingRange(8)
    );

    public static <T extends Entity> EntityType<T> registerEntity(String name, EntityType.Builder<T> entityTypeBuilder) {
        EntityType<T> entityType = entityTypeBuilder.build(name);
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(Hunted.MOD_ID, name), entityType);
    }
}
