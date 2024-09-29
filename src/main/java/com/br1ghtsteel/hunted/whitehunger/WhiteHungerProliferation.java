package com.br1ghtsteel.hunted.whitehunger;

import com.br1ghtsteel.hunted.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class WhiteHungerProliferation {

    private static final Map<Block, Block> whiteHungerBlockConversions = new HashMap<>();

    public static void initializeWhiteHungerProliferation() {
        whiteHungerBlockConversions.put(Blocks.DIRT, ModBlocks.WHITE_HYPHAE_DIRT);
        whiteHungerBlockConversions.put(Blocks.ROOTED_DIRT, ModBlocks.WHITE_HYPHAE_DIRT);
        whiteHungerBlockConversions.put(Blocks.GRASS_BLOCK, ModBlocks.WHITE_MYCELIUM);
        whiteHungerBlockConversions.put(Blocks.MYCELIUM, ModBlocks.WHITE_MYCELIUM);
        whiteHungerBlockConversions.put(Blocks.PODZOL, ModBlocks.WHITE_MYCELIUM);
    }

    @Nullable
    public static Block getBlockConversion(Block block) {
        return whiteHungerBlockConversions.get(block);
    }
}

/**
 * Sky Color Reference
 *  - FILE: ClientWorld.java
 *  - FUNCTION: getSkyColor()
 *  - USED IN: renderSky() in WorldRenderer
 */