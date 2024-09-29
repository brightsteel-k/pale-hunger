package com.br1ghtsteel.hunted.whitehunger;

import com.br1ghtsteel.hunted.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class WhiteHungerProliferation {

    private static final Map<Block, Block> whiteHungerBlockConversions = new HashMap<>();
    private static final Map<Block, Block> whiteHungerDecorationConversions = new HashMap<>();
    private static boolean whiteHungerSpreadEnabled = true;

    public static void initializeWhiteHungerProliferation() {
        whiteHungerBlockConversions.put(Blocks.DIRT, ModBlocks.WHITE_HYPHAE_DIRT);
        whiteHungerBlockConversions.put(Blocks.ROOTED_DIRT, ModBlocks.WHITE_HYPHAE_DIRT);
        whiteHungerBlockConversions.put(Blocks.GRASS_BLOCK, ModBlocks.WHITE_MYCELIUM);
        whiteHungerBlockConversions.put(Blocks.MYCELIUM, ModBlocks.WHITE_MYCELIUM);
        whiteHungerBlockConversions.put(Blocks.PODZOL, ModBlocks.WHITE_MYCELIUM);

        whiteHungerDecorationConversions.put(Blocks.GRASS, ModBlocks.WHITEGRASS);
        whiteHungerDecorationConversions.put(Blocks.POPPY, ModBlocks.WHITE_FUNGUS);
    }

    @Nullable
    public static Block getBlockConversion(Block block) {
        return whiteHungerBlockConversions.get(block);
    }

    public static void convertBlock(World world, BlockPos blockPos, BlockState fromBlock) {
        Block toBlock = whiteHungerBlockConversions.get(fromBlock.getBlock());
        if (toBlock != null) {
            convertBlock(world, blockPos, fromBlock, toBlock.getDefaultState());
        }
    }

    public static void convertBlock(World world, BlockPos blockPos, BlockState fromBlock, BlockState toBlock) {
        checkAboveConvertedBlock(world, blockPos);
        world.setBlockState(blockPos, toBlock);
    }

    public static void checkAboveConvertedBlock(World world, BlockPos blockPos) {
        BlockPos aboveBlockPos = blockPos.up();
        BlockState aboveBlockState = world.getBlockState(aboveBlockPos);

        // Decorations
        Block toBlock = whiteHungerDecorationConversions.get(aboveBlockState.getBlock());
        if (toBlock != null) {
            world.setBlockState(aboveBlockPos, toBlock.getDefaultState());
            return;
        }

        // Flowers
        if (aboveBlockState.isIn(BlockTags.FLOWERS)) {
            toBlock = whiteHungerDecorationConversions.get(Blocks.POPPY);
            world.setBlockState(aboveBlockPos, toBlock.getDefaultState());
            return;
        }

        // Tallgrass
        if (aboveBlockState.getBlock() == Blocks.TALL_GRASS) {
            world.setBlockState(aboveBlockPos, ModBlocks.TALL_WHITEGRASS.getDefaultState(), 0);
            world.setBlockState(aboveBlockPos.up(), ModBlocks.TALL_WHITEGRASS.getDefaultState().with(TallPlantBlock.HALF, DoubleBlockHalf.UPPER), 0);
        }
    }

    public static void setEnabled(boolean enabled) {
        whiteHungerSpreadEnabled = enabled;
    }

    public static boolean isEnabled() {
        return whiteHungerSpreadEnabled;
    }
}

/**
 * Sky Color Reference
 *  - FILE: ClientWorld.java
 *  - FUNCTION: getSkyColor()
 *  - USED IN: renderSky() in WorldRenderer
 */