package com.br1ghtsteel.hunted.entity.client;

import com.br1ghtsteel.hunted.entity.LurcherEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.feature.EnergySwirlOverlayFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class LurcherChargeFeatureRenderer extends EnergySwirlOverlayFeatureRenderer<LurcherEntity, LurcherModel<LurcherEntity>> {
    private static final Identifier SKIN = new Identifier("textures/entity/creeper/creeper_armor.png");
    private final LurcherModel<LurcherEntity> model;

    public LurcherChargeFeatureRenderer(FeatureRendererContext<LurcherEntity, LurcherModel<LurcherEntity>> context, EntityModelLoader loader) {
        super(context);
        this.model = new LurcherModel<>(loader.getModelPart(ModModelLayers.LURCHER_ARMOR));
    }

    @Override
    protected float getEnergySwirlX(float partialAge) {
        return partialAge * 0.01F;
    }

    @Override
    protected Identifier getEnergySwirlTexture() {
        return SKIN;
    }

    @Override
    protected EntityModel<LurcherEntity> getEnergySwirlModel() {
        return this.model;
    }
}
