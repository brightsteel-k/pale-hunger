package com.br1ghtsteel.palehunger.item;

import com.br1ghtsteel.palehunger.Hunted;
import com.br1ghtsteel.palehunger.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup HUNTED = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Hunted.MOD_ID, "hunted"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.hunted"))
                    .icon(() -> new ItemStack(ModBlocks.PALE_FUNGUS))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.DROSS_ROSE);
                        entries.add(ModBlocks.PALE_HYPHAE_DIRT);
                        entries.add(ModBlocks.PALE_MYCELIUM);
                        entries.add(ModBlocks.PALE_LEAVES);
                        entries.add(ModBlocks.PALE_FUNGUS);
                        entries.add(ModBlocks.TALL_PALE_FUNGUS);
                        entries.add(ModBlocks.PALE_GRASS);
                        entries.add(ModBlocks.TALL_PALE_GRASS);
                        entries.add(ModItems.PALE_GROWTH);
                        entries.add(ModItems.PALE_CONK);
                        entries.add(ModBlocks.PALE_TENDRILS);
                        entries.add(ModBlocks.PALE_VEIN);
                        entries.add(ModItems.GHOUL_SPAWN_EGG);
                        entries.add(ModItems.LURCHER_SPAWN_EGG);
                    }).build());

    public static void registerItemGroups() {
        Hunted.LOGGER.info("Registering Item Groups for: " + Hunted.MOD_ID);
    }
}
