package com.br1ghtsteel.palehunger.item;

import com.br1ghtsteel.palehunger.ThePaleHunger;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    /*public static final Item COPPER_BAR = registerItem("copper_bar",
            new BarBlockItem(ModBlocks.COPPER_BAR_STRAIGHT, ModBlocks.COPPER_BAR_CORNER, new FabricItemSettings()));*/

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        // entries.add(COPPER_BAR);
    }

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ThePaleHunger.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ThePaleHunger.LOGGER.info("Registering Mod Items for: " + ThePaleHunger.MOD_ID);

        // ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
