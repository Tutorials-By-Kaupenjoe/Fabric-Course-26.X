package net.kaupenjoe.mccourse.event;

import net.kaupenjoe.mccourse.keybind.ModKeyMappings;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class ModClientEvents {
    public static void onEndTick(Minecraft client) {
        while (ModKeyMappings.K_KEYBIND.consumeClick()) {
            client.player.sendSystemMessage(Component.literal("I just pressed the Kaupen Key (Default: K)"));
        }
    }
}
