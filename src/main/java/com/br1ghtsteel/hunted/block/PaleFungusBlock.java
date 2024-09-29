package com.br1ghtsteel.hunted.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class PaleFungusBlock extends PlantBlock {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 9.0, 12.0);
    private final Block mycelium;

    public PaleFungusBlock(AbstractBlock.Settings settings, Block mycelium) {
        super(settings);
        this.mycelium = mycelium;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(mycelium) || floor.isOf(Blocks.MYCELIUM) || super.canPlantOnTop(floor, world, pos);
    }
}
