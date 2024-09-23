package com.br1ghtsteel.palehunger.block;

import com.br1ghtsteel.palehunger.Hunted;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block DROSS_ROSE = registerBlock("dross_rose",
            new FlowerBlock(StatusEffects.HUNGER, 60, FabricBlockSettings.copyOf(Blocks.POPPY)));
    public static final Block PALE_LEAVES = registerBlock("pale_leaves",
            new PaleLeavesBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.WHITE)
                            .strength(0.2F)
                            .sounds(BlockSoundGroup.WET_GRASS)
                            .nonOpaque()
                            .allowsSpawning(Blocks::canSpawnOnLeaves)
                            .suffocates(Blocks::never)
                            .blockVision(Blocks::never)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .solidBlock(Blocks::never)
            )
    );
    public static final Block PALE_HYPHAE_DIRT = registerBlock("pale_hyphae_dirt",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.DIRT_BROWN).strength(0.5F).sounds(BlockSoundGroup.GRAVEL))
    );
    public static final Block PALE_MYCELIUM = registerBlock("pale_mycelium",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.DIRT_BROWN).strength(0.5F).sounds(BlockSoundGroup.GRAVEL))
    );
    public static final Block PALE_FUNGUS = registerBlock("pale_fungus",
            new PaleFungusBlock(
                    AbstractBlock.Settings.create().mapColor(MapColor.WHITE).breakInstantly().noCollision().sounds(BlockSoundGroup.FUNGUS).pistonBehavior(PistonBehavior.DESTROY),
                    PALE_MYCELIUM
            )
    );
    public static final Block TALL_PALE_FUNGUS = registerBlock("tall_pale_fungus",
            new TallPaleFungusBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.WHITE)
                            .replaceable()
                            .noCollision()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.FUNGUS)
                            .offset(AbstractBlock.OffsetType.XZ)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block PALE_GRASS = registerBlock("pale_grass",
            new FernBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.WHITE)
                            .replaceable()
                            .noCollision()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.WET_GRASS)
                            .offset(AbstractBlock.OffsetType.XYZ)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block TALL_PALE_GRASS = registerBlock("tall_pale_grass",
            new TallPlantBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.WHITE)
                            .replaceable()
                            .noCollision()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.WET_GRASS)
                            .offset(AbstractBlock.OffsetType.XZ)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block PALE_GROWTH = registerBlock("pale_growth",
            new ShelfFungusBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.WHITE)
                            .noCollision()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.WET_GRASS)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
            ), false
    );
    public static final Block PALE_WALL_GROWTH = registerBlock("pale_wall_growth",
            new WallShelfFungusBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.WHITE)
                            .noCollision()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.WET_GRASS)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .dropsLike(PALE_GROWTH)
            ), false
    );
    public static final Block PALE_CONK = registerBlock("pale_conk",
            new ShelfFungusBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.WHITE)
                            .noCollision()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.FUNGUS)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
            ), false
    );
    public static final Block PALE_WALL_CONK = registerBlock("pale_wall_conk",
            new WallShelfFungusBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.WHITE)
                            .noCollision()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.FUNGUS)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .dropsLike(PALE_CONK)
            ), false
    );
    public static final Block PALE_TENDRILS = registerBlock("pale_tendrils",
            new PaleTendrilsBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.WHITE)
                            .replaceable()
                            .noCollision()
                            .ticksRandomly()
                            .strength(0.2F)
                            .sounds(BlockSoundGroup.VINE)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block PALE_VEIN = registerBlock(
            "pale_vein",
            new PaleVeinBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.WHITE)
                            .solid()
                            .noCollision()
                            .strength(0.2F)
                            .sounds(BlockSoundGroup.SCULK_VEIN)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );

    public static Block registerBlock(String name, Block block) {
        return registerBlock(name, block, true);
    }

    public static Block registerBlock(String name, Block block, boolean shouldMakeBlockItem) {
        if (shouldMakeBlockItem) {
            registerBlockItem(name, block);
        }
        return Registry.register(Registries.BLOCK, new Identifier(Hunted.MOD_ID, name), block);
    }

    public static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Hunted.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Hunted.LOGGER.info("Registering Mod Blocks for: " + Hunted.MOD_ID);
    }
}
