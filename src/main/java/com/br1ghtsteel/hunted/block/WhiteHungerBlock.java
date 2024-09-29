package com.br1ghtsteel.hunted.block;

import com.br1ghtsteel.hunted.Hunted;
import com.br1ghtsteel.hunted.whitehunger.WhiteHungerProliferation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class WhiteHungerBlock extends Block {

    public boolean shouldGrow;

    public WhiteHungerBlock(Settings settings, boolean shouldGrow) {
        super(settings);
        this.shouldGrow = shouldGrow;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);
        tryGrow(state, world, pos, random);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (this.shouldGrow && !world.isClient() && WhiteHungerProliferation.isEnabled() && WhiteHungerProliferation.getBlockConversion(neighborState.getBlock()) != null) {
            world.scheduleBlockTick(pos, state.getBlock(), getWhiteHungerUpdateTickDelay(world.getRandom()));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (this.shouldGrow && !world.isClient() && WhiteHungerProliferation.isEnabled()) {
            world.scheduleBlockTick(pos, state.getBlock(), getWhiteHungerUpdateTickDelay(world.getRandom()));
        }
        super.onBlockAdded(state, world, pos, oldState, notify);
    }

    private static final int MAX_DIST = 3;

    public static void tryGrow(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        Hunted.sendChatMessage("TryGrow called at: " + pos.toString());
        boolean shouldRevisit = false;

        // Check for nearby noninfected blocks
        BlockPos.Mutable mutableBlockPos = new BlockPos.Mutable();
        for (int x = -MAX_DIST; x <= MAX_DIST; x++) {
            int dx = Math.abs(x);
            for (int y = -MAX_DIST + dx; y <= MAX_DIST - dx; y++) {
                int dy = Math.abs(y);
                for (int z = -MAX_DIST + dx + dy; z <= MAX_DIST - dx - dy; z++) {
                    int dz = Math.abs(z);
                    int distance = dx + dy + dz;
                    mutableBlockPos.set(pos, x, y, z);

                    // Try to grow to block
                    if (!tryGrowToBlock(mutableBlockPos, world, random, distance)) {
                        // Mark this block as requiring a revisit, unless it is the furthest distance away
                        if (distance < MAX_DIST || random.nextBoolean()) {
                            shouldRevisit = true;
                        }
                    }
                }
            }
        }

        if (shouldRevisit) {
            world.scheduleBlockTick(pos, state.getBlock(), getWhiteHungerUpdateTickDelay(random));
        }
    }

    /**
     * Tries to convert the given block to its white hunger infected variant.
     * @param blockPos position of the given block.
     * @param world world with the given block.
     * @param distance Manhattan distance from the white hunger block doing the infecting.
     * @return false if a convertable block was skipped, true otherwise.
     */
    public static boolean tryGrowToBlock(BlockPos.Mutable blockPos, ServerWorld world, Random random, int distance) {
        BlockState targetBlockState = world.getBlockState(blockPos);

        // Check if block is already a white hunger block
        if (targetBlockState.getBlock() instanceof WhiteHungerBlock) {
            return true;
        }

        Block convertedBlock = WhiteHungerProliferation.getBlockConversion(targetBlockState.getBlock());
        // Check if block cannot be converted
        if (convertedBlock == null) {
            return true;
        }

        // Try to convert the block
        if (shouldConvertBlock(random, distance)) {
            WhiteHungerProliferation.convertBlock(world, blockPos, targetBlockState, convertedBlock.getDefaultState());
            return true;
        } else {
            return false;
        }
    }

    public static boolean shouldConvertBlock(Random random, int distance) {
        double p = (double)distance / (double)(MAX_DIST + 1);
        p = 1 - Math.pow(p, 3);
        return random.nextDouble() <= p;
    }

    public static int getWhiteHungerUpdateTickDelay(Random random) {
        return 160 + random.nextInt(480);
    }
}
