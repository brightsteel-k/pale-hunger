package com.br1ghtsteel.hunted.entity.client;

import com.br1ghtsteel.hunted.Hunted;
import com.br1ghtsteel.hunted.entity.GhoulEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class GhoulOverlayFeatureRenderer<T extends GhoulEntity> extends FeatureRenderer<T, SkinfeederModel<T>> {
    private static final Identifier SKIN = new Identifier(Hunted.MOD_ID, "textures/entity/ghoul_outer_layer.png");
    private final SkinfeederModel<T> model;

    public GhoulOverlayFeatureRenderer(FeatureRendererContext<T, SkinfeederModel<T>> context, EntityModelLoader loader) {
        super(context);
        this.model = new SkinfeederModel<>(loader.getModelPart(ModModelLayers.GHOUL_OUTER));
    }

    public void render(
            MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T drownedEntity, float f, float g, float h, float j, float k, float l
    ) {
        render(this.getContextModel(), this.model, SKIN, matrixStack, vertexConsumerProvider, i, drownedEntity, f, g, j, k, l, h, 1.0F, 1.0F, 1.0F);
    }
}