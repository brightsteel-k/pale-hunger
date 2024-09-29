package com.br1ghtsteel.hunted;

import com.br1ghtsteel.hunted.block.ModBlocks;
import com.br1ghtsteel.hunted.entity.LurcherEntity;
import com.br1ghtsteel.hunted.entity.ModEntities;
import com.br1ghtsteel.hunted.entity.GhoulEntity;
import com.br1ghtsteel.hunted.item.ModItemGroups;
import com.br1ghtsteel.hunted.item.ModItems;
import com.br1ghtsteel.hunted.whitehunger.WhiteHungerProliferation;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hunted implements ModInitializer {
    public static final String MOD_ID = "hunted";
    public static final Logger LOGGER = LoggerFactory.getLogger("hunted");

	@Override
	public void onInitialize() {

		LOGGER.info("Hello Fabric world!");

        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        WhiteHungerProliferation.initializeWhiteHungerProliferation();

        FabricDefaultAttributeRegistry.register(ModEntities.GHOUL_ENTITY, GhoulEntity.createGhoulAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.LURCHER_ENTITY, LurcherEntity.createLurcherAttributes());
	}

    public static void sendChatMessage(String message) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.inGameHud.getChatHud().addMessage(Text.literal(message));
    }
}