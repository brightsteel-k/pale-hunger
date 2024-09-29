package com.br1ghtsteel.hunted.block;


import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

import java.util.Collection;

public class PaleVeinBlock extends MultifaceGrowthBlock implements Waterloggable {
    private static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private final LichenGrower allGrowTypeGrower = new LichenGrower(new PaleVeinGrowChecker(LichenGrower.GROW_TYPES));
    private final LichenGrower samePositionOnlyGrower = new LichenGrower(new PaleVeinGrowChecker(LichenGrower.GrowType.SAME_POSITION));

    public PaleVeinBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public LichenGrower getGrower() {
        return this.allGrowTypeGrower;
    }

    public LichenGrower getSamePositionOnlyGrower() {
        return this.samePositionOnlyGrower;
    }

    public static boolean place(WorldAccess world, BlockPos pos, BlockState state, Collection<Direction> directions) {
        boolean bl = false;
        BlockState blockState = ModBlocks.PALE_VEIN.getDefaultState();

        for (Direction direction : directions) {
            BlockPos blockPos = pos.offset(direction);
            if (canGrowOn(world, direction, blockPos, world.getBlockState(blockPos))) {
                blockState = blockState.with(getProperty(direction), Boolean.valueOf(true));
                bl = true;
            }
        }

        if (!bl) {
            return false;
        } else {
            if (!state.getFluidState().isEmpty()) {
                blockState = blockState.with(WATERLOGGED, Boolean.valueOf(true));
            }

            world.setBlockState(pos, blockState, Block.NOTIFY_ALL);
            return true;
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if ((Boolean)state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    class PaleVeinGrowChecker extends LichenGrower.LichenGrowChecker {
        private final LichenGrower.GrowType[] growTypes;

        public PaleVeinGrowChecker(LichenGrower.GrowType... growTypes) {
            super(PaleVeinBlock.this);
            this.growTypes = growTypes;
        }

        @Override
        public boolean canGrow(BlockView world, BlockPos pos, BlockPos growPos, Direction direction, BlockState state) {
            BlockState blockState = world.getBlockState(growPos.offset(direction));
            if (!blockState.isOf(Blocks.MOVING_PISTON)) {
                if (pos.getManhattanDistance(growPos) == 2) {
                    BlockPos blockPos = pos.offset(direction.getOpposite());
                    if (world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, direction)) {
                        return false;
                    }
                }

                FluidState fluidState = state.getFluidState();
                if (!fluidState.isEmpty() && !fluidState.isOf(Fluids.WATER)) {
                    return false;
                } else {
                    return state.isIn(BlockTags.FIRE) ? false : state.isReplaceable() || super.canGrow(world, pos, growPos, direction, state);
                }
            } else {
                return false;
            }
        }

        @Override
        public LichenGrower.GrowType[] getGrowTypes() {
            return this.growTypes;
        }

        @Override
        public boolean canGrow(BlockState state) {
            return !state.isOf(Blocks.SCULK_VEIN);
        }
    }
}
