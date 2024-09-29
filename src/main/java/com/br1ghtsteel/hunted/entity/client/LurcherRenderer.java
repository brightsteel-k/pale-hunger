package com.br1ghtsteel.hunted.entity.client;

import com.br1ghtsteel.hunted.Hunted;
import com.br1ghtsteel.hunted.entity.LurcherEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class LurcherRenderer extends MobEntityRenderer<LurcherEntity, LurcherModel<LurcherEntity>> {
    private static final Identifier TEXTURE = new Identifier(Hunted.MOD_ID, "textures/entity/lurcher.png");

    public LurcherRenderer(EntityRendererFactory.Context context) {
        super(context, new LurcherModel<>(context.getPart(ModModelLayers.LURCHER)), 0.5f);
        this.addFeature(new LurcherChargeFeatureRenderer(this, context.getModelLoader()));
    }

    @Override
    protected void scale(LurcherEntity entity, MatrixStack matrixStack, float amount) {
        float g = entity.getClientFuseTime(amount);
        float h = 1.0F + MathHelper.sin(g * 100.0F) * g * 0.01F;
        g = MathHelper.clamp(g, 0.0F, 1.0F);
        g *= g;
        g *= g;
        float i = (1.0F + g * 0.4F) * h;
        float j = (1.0F + g * 0.1F) / h;
        matrixStack.scale(i, j, i);
    }

    @Override
    public Identifier getTexture(LurcherEntity entity) {
        return TEXTURE;
    }
}
