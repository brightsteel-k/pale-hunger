package com.br1ghtsteel.hunted.tags;

import com.br1ghtsteel.hunted.Hunted;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {

    // public static final TagKey<Block> PALE_FUNGUS_BASE_BLOCKS = of("pale_fungus_base_blocks");

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(Hunted.MOD_ID, id));
    }
}
