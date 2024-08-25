package com.br1ghtsteel.palehunger.item;

import com.br1ghtsteel.palehunger.Hunted;
import com.br1ghtsteel.palehunger.entity.ModEntities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item SKINFEEDER_SPAWN_EGG = registerItem("skinfeeder_spawn_egg",
            new SpawnEggItem(ModEntities.SKINFEEDER_ENTITY, 0xad9e95, 0xdf3c3f, new FabricItemSettings()));

    private static void addItemsToSpawnEggItemGroup(FabricItemGroupEntries entries) {
        entries.add(SKINFEEDER_SPAWN_EGG);
    }

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Hunted.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Hunted.LOGGER.info("Registering Mod Items for: " + Hunted.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(ModItems::addItemsToSpawnEggItemGroup);
    }
}
