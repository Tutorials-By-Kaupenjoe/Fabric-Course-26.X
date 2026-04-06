package net.kaupenjoe.mccourse.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.kaupenjoe.mccourse.keybind.ModKeyMappings;
import net.kaupenjoe.mccourse.networking.packet.TestPayloadC2S;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class ModClientEvents {
    public static void onEndTick(Minecraft client) {
        while (ModKeyMappings.K_KEYBIND.consumeClick()) {
            client.player.sendSystemMessage(Component.literal("I just pressed the Kaupen Key (Default: K)"));
            ClientPlayNetworking.send(new TestPayloadC2S("Kaupenjoe", 42));
        }
    }
}
