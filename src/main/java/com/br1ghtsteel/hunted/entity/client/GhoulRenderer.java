package com.br1ghtsteel.hunted.entity.client;

import com.br1ghtsteel.hunted.Hunted;
import com.br1ghtsteel.hunted.entity.GhoulEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

public class GhoulRenderer extends ZombieBaseEntityRenderer<GhoulEntity, SkinfeederModel<GhoulEntity>> {
    private static final Identifier TEXTURE = new Identifier(Hunted.MOD_ID, "textures/entity/ghoul.png");

    public GhoulRenderer(EntityRendererFactory.Context context) {
        super(
                context,
                new SkinfeederModel<>(context.getPart(ModModelLayers.GHOUL)),
                new SkinfeederModel<>(context.getPart(ModModelLayers.GHOUL_INNER_ARMOR)),
                new SkinfeederModel<>(context.getPart(ModModelLayers.GHOUL_OUTER_ARMOR))
        );
        this.addFeature(new GhoulOverlayFeatureRenderer<>(this, context.getModelLoader()));
    }

    @Override
    protected void scale(GhoulEntity entity, MatrixStack matrixStack, float amount) {
        float g = 1.0625F;
        matrixStack.scale(1.0625F, 1.0625F, 1.0625F);
        super.scale(entity, matrixStack, amount);
    }

    @Override
    public Identifier getTexture(ZombieEntity zombieEntity) {
        return TEXTURE;
    }
}
