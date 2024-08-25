package com.br1ghtsteel.palehunger;

import com.br1ghtsteel.palehunger.block.ModBlocks;
import com.br1ghtsteel.palehunger.entity.ModEntities;
import com.br1ghtsteel.palehunger.entity.SkinfeederEntity;
import com.br1ghtsteel.palehunger.item.ModItemGroups;
import com.br1ghtsteel.palehunger.item.ModItems;
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

        FabricDefaultAttributeRegistry.register(ModEntities.SKINFEEDER_ENTITY, SkinfeederEntity.createSkinfeederAttributes());
	}

    public static void sendChatMessage(String message) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.inGameHud.getChatHud().addMessage(Text.literal(message));
    }
}