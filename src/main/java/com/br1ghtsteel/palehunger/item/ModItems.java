package com.br1ghtsteel.palehunger.item;

import com.br1ghtsteel.palehunger.Hunted;
import com.br1ghtsteel.palehunger.block.ModBlocks;
import com.br1ghtsteel.palehunger.entity.ModEntities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class ModItems {
    public static final Item GHOUL_SPAWN_EGG = registerItem("ghoul_spawn_egg",
            new SpawnEggItem(ModEntities.GHOUL_ENTITY, 0xad9e95, 0xdf3c3f, new FabricItemSettings()));
    public static final Item LURCHER_SPAWN_EGG = registerItem("lurcher_spawn_egg",
            new SpawnEggItem(ModEntities.LURCHER_ENTITY, 0xad9e95, 0xdf3c3f, new FabricItemSettings()));
    public static final Item PALE_GROWTH = registerItem("pale_growth", new VerticallyAttachableBlockItem(ModBlocks.PALE_GROWTH, ModBlocks.PALE_WALL_GROWTH, new Item.Settings(), Direction.DOWN));
    public static final Item PALE_CONK = registerItem("pale_conk", new VerticallyAttachableBlockItem(ModBlocks.PALE_CONK, ModBlocks.PALE_WALL_CONK, new Item.Settings(), Direction.DOWN));

    private static void addItemsToSpawnEggItemGroup(FabricItemGroupEntries entries) {
        entries.add(GHOUL_SPAWN_EGG);
        entries.add(LURCHER_SPAWN_EGG);
    }

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Hunted.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Hunted.LOGGER.info("Registering Mod Items for: " + Hunted.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(ModItems::addItemsToSpawnEggItemGroup);
    }
}
