package com.br1ghtsteel.palehunger.entity.client;

import com.br1ghtsteel.palehunger.Hunted;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {

    public static final EntityModelLayer SKINFEEDER = register("skinfeeder");
    public static final EntityModelLayer SKINFEEDER_OUTER = register("skinfeeder", "outer");
    public static final EntityModelLayer SKINFEEDER_INNER_ARMOR = register("skinfeeder", "inner_armor");
    public static final EntityModelLayer SKINFEEDER_OUTER_ARMOR = register("skinfeeder", "outer_armor");

    private static EntityModelLayer register(String id) {
        return register(id, "main");
    }

    private static EntityModelLayer register(String id, String type) {
        return new EntityModelLayer(new Identifier(Hunted.MOD_ID, id), type);
    }
}
