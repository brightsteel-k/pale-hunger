package com.br1ghtsteel.palehunger.entity.client;

import com.br1ghtsteel.palehunger.Hunted;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {

    public static final EntityModelLayer GHOUL = register("ghoul");
    public static final EntityModelLayer GHOUL_OUTER = register("ghoul", "outer");
    public static final EntityModelLayer GHOUL_INNER_ARMOR = register("ghoul", "inner_armor");
    public static final EntityModelLayer GHOUL_OUTER_ARMOR = register("ghoul", "outer_armor");
    public static final EntityModelLayer LURCHER = register("lurcher");
    public static final EntityModelLayer LURCHER_ARMOR = register("lurcher", "armor");
    public static final EntityModelLayer LURCHER_HEAD = register("lurcher_head");

    private static EntityModelLayer register(String id) {
        return register(id, "main");
    }

    private static EntityModelLayer register(String id, String type) {
        return new EntityModelLayer(new Identifier(Hunted.MOD_ID, id), type);
    }
}
