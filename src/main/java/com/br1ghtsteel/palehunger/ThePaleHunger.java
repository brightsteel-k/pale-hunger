package com.br1ghtsteel.palehunger;

import com.br1ghtsteel.palehunger.block.ModBlocks;
import com.br1ghtsteel.palehunger.item.ModItemGroups;
import com.br1ghtsteel.palehunger.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThePaleHunger implements ModInitializer {
    public static final String MOD_ID = "palehunger";
    public static final Logger LOGGER = LoggerFactory.getLogger("palehunger");

	@Override
	public void onInitialize() {

		LOGGER.info("Hello Fabric world!");

        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
	}
}