package com.br1ghtsteel.palehunger.block;

import com.br1ghtsteel.palehunger.Hunted;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block DROSS_ROSE = registerBlock("dross_rose",
            new FlowerBlock(StatusEffects.HUNGER, 60, FabricBlockSettings.copyOf(Blocks.POPPY)));

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
