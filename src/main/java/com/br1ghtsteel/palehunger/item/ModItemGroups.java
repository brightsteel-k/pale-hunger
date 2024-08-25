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
    public static final ItemGroup AMBER_AND_COPPER = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Hunted.MOD_ID, "hunted"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.hunted"))
                    .icon(() -> new ItemStack(ModBlocks.DROSS_ROSE))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.DROSS_ROSE);
                        entries.add(ModItems.SKINFEEDER_SPAWN_EGG);
                    }).build());

    public static void registerItemGroups() {
        Hunted.LOGGER.info("Registering Item Groups for: " + Hunted.MOD_ID);
    }
}
