package com.br1ghtsteel.palehunger;

import com.br1ghtsteel.palehunger.block.ModBlocks;
import com.br1ghtsteel.palehunger.entity.ModEntities;
import com.br1ghtsteel.palehunger.entity.client.ModModelLayers;
import com.br1ghtsteel.palehunger.entity.client.SkinfeederModel;
import com.br1ghtsteel.palehunger.entity.client.SkinfeederRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class HuntedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DROSS_ROSE, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.SKINFEEDER_ENTITY, SkinfeederRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SKINFEEDER, SkinfeederModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SKINFEEDER_OUTER, SkinfeederModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SKINFEEDER_INNER_ARMOR, SkinfeederModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SKINFEEDER_OUTER_ARMOR, SkinfeederModel::getTexturedModelData);
    }
}
