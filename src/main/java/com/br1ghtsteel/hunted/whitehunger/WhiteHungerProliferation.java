package com.br1ghtsteel.hunted.whitehunger;

import com.br1ghtsteel.hunted.block.ModBlocks;
import com.br1ghtsteel.hunted.tags.ModBlockTags;
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

    private static final Map<Block, Block> whiteHungerDecorationConversions = new HashMap<>();
    private static boolean whiteHungerSpreadEnabled = true;
    public static int growCallCount = 0;
    public static int growCallsLastCount = 0;

    public static void initializeWhiteHungerProliferation() {
        whiteHungerDecorationConversions.put(Blocks.GRASS, ModBlocks.WHITEGRASS);
        whiteHungerDecorationConversions.put(Blocks.POPPY, ModBlocks.WHITE_FUNGUS);
    }

    @Nullable
    public static BlockState getBlockConversion(BlockState blockState) {
        if (convertsToWhiteMycelium(blockState)) {
            return ModBlocks.WHITE_MYCELIUM.getDefaultState();
        } else if (convertsToWhiteHyphaeDirt(blockState)) {
            return ModBlocks.WHITE_HYPHAE_DIRT.getDefaultState();
        }
        return null;
    }

    public static boolean convertsToWhiteMycelium(BlockState blockState) {
        return blockState.isIn(ModBlockTags.WHITEHUNGER_TO_WHITE_MYCELIUM);
    }

    public static boolean convertsToWhiteHyphaeDirt(BlockState blockState) {
        return blockState.isIn(ModBlockTags.WHITEHUNGER_TO_WHITE_HYPHAE_DIRT);
    }

    public static void convertFromBlock(World world, BlockPos blockPos, BlockState fromBlockState) {
        BlockState toBlockState = getBlockConversion(fromBlockState);
        if (toBlockState != null) {
            convertToBlock(world, blockPos, toBlockState);
        }
    }

    public static void convertToBlock(World world, BlockPos blockPos, BlockState toBlockState) {
        world.setBlockState(blockPos, toBlockState);
        checkAboveConvertedBlock(world, blockPos);
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

    public static int getGrowCallCount() {
        return growCallCount;
    }

    public static int countGrowCallsSinceLastCount() {
        int callsSinceLastCount = growCallCount - growCallsLastCount;
        growCallsLastCount = growCallCount;
        return callsSinceLastCount;
    }

    public static void clearCount() {
        growCallCount = 0;
    }

    public static void logGrowCall() {
        growCallCount++;
    }
}

/**
 * Sky Color Reference
 *  - FILE: ClientWorld.java
 *  - FUNCTION: getSkyColor()
 *  - USED IN: renderSky() in WorldRenderer
 */