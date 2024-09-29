package com.br1ghtsteel.hunted;

import com.br1ghtsteel.hunted.block.ModBlocks;
import com.br1ghtsteel.hunted.entity.ModEntities;
import com.br1ghtsteel.hunted.entity.client.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class HuntedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DROSS_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PALE_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PALE_FUNGUS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TALL_PALE_FUNGUS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PALE_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TALL_PALE_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PALE_GROWTH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PALE_WALL_GROWTH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PALE_CONK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PALE_WALL_CONK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PALE_TENDRILS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PALE_VEIN, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.GHOUL_ENTITY, GhoulRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GHOUL, SkinfeederModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GHOUL_OUTER, SkinfeederModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GHOUL_INNER_ARMOR, SkinfeederModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GHOUL_OUTER_ARMOR, SkinfeederModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.LURCHER_ENTITY, LurcherRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.LURCHER, LurcherModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.LURCHER_ARMOR, LurcherModel::getTexturedModelData);
    }
}
