package com.br1ghtsteel.palehunger.item;

import com.br1ghtsteel.palehunger.ThePaleHunger;
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
            new Identifier(ThePaleHunger.MOD_ID, "pale_hunger"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.pale_hunger"))
                    .icon(() -> new ItemStack(ModBlocks.DROSS_ROSE))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.DROSS_ROSE);
                        entries.add(ModItems.SKINFEEDER_SPAWN_EGG);
                    }).build());

    public static void registerItemGroups() {
        ThePaleHunger.LOGGER.info("Registering Item Groups for: " + ThePaleHunger.MOD_ID);
    }
}
