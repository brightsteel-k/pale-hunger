package com.br1ghtsteel.palehunger;

import com.br1ghtsteel.palehunger.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ThePaleHungerClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DROSS_ROSE, RenderLayer.getCutout());
    }
}
