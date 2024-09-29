package com.br1ghtsteel.hunted.tags;

import com.br1ghtsteel.hunted.Hunted;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {

    public static final TagKey<Block> WHITEHUNGER_TO_WHITE_MYCELIUM = of("whitehunger_to_white_mycelium");
    public static final TagKey<Block> WHITEHUNGER_TO_WHITE_HYPHAE_DIRT = of("whitehunger_to_white_hyphae_dirt");

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(Hunted.MOD_ID, id));
    }
}
