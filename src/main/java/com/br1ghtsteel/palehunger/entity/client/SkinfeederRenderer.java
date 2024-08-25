package com.br1ghtsteel.palehunger.entity.client;

import com.br1ghtsteel.palehunger.Hunted;
import com.br1ghtsteel.palehunger.entity.SkinfeederEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

public class SkinfeederRenderer extends ZombieBaseEntityRenderer<SkinfeederEntity, SkinfeederModel<SkinfeederEntity>> {
    private static final Identifier TEXTURE = new Identifier(Hunted.MOD_ID, "textures/entity/skinfeeder.png");

    public SkinfeederRenderer(EntityRendererFactory.Context context) {
        super(
                context,
                new SkinfeederModel<>(context.getPart(ModModelLayers.SKINFEEDER)),
                new SkinfeederModel<>(context.getPart(ModModelLayers.SKINFEEDER_INNER_ARMOR)),
                new SkinfeederModel<>(context.getPart(ModModelLayers.SKINFEEDER_OUTER_ARMOR))
        );
        this.addFeature(new SkinfeederOverlayFeatureRenderer<>(this, context.getModelLoader()));
    }

    @Override
    protected void scale(SkinfeederEntity entity, MatrixStack matrixStack, float amount) {
        float g = 1.0625F;
        matrixStack.scale(1.0625F, 1.0625F, 1.0625F);
        super.scale(entity, matrixStack, amount);
    }

    @Override
    public Identifier getTexture(ZombieEntity zombieEntity) {
        return TEXTURE;
    }
}
