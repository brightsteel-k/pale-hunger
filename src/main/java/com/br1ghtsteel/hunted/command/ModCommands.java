package com.br1ghtsteel.hunted.command;

import com.br1ghtsteel.hunted.whitehunger.WhiteHungerProliferation;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;

public class ModCommands {

    public static void registerModCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("whitehunger")
                .then(literal("growcalls")
                        .then(literal("counttotal")
                                .executes((ctx) -> {
                                    int growCallCount = WhiteHungerProliferation.getGrowCallCount();
                                    ctx.getSource().sendFeedback(() -> Text.literal("White Hunger Grow calls: " + growCallCount), false);
                                    return growCallCount;
                                }))
                        .then(literal("countsincelastcount").executes((ctx) -> {
                                    int growCallCount = WhiteHungerProliferation.countGrowCallsSinceLastCount();
                                    ctx.getSource().sendFeedback(() -> Text.literal("White Hunger Grow calls since last count: " + growCallCount), false);
                                    return growCallCount;
                                }))
                        .then(literal("clearcount").executes((ctx) -> {
                            WhiteHungerProliferation.clearCount();
                            ctx.getSource().sendFeedback(() -> Text.literal("White Hunger Grow call count cleared."), false);
                            return 1;
                        })))));
    }
}
