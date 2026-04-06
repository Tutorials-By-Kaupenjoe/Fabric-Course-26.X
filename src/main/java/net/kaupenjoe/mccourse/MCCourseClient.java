package net.kaupenjoe.mccourse;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.kaupenjoe.mccourse.event.ModClientEvents;
import net.kaupenjoe.mccourse.keybind.ModKeyMappings;
import net.minecraft.resources.Identifier;

public class MCCourseClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeyMappings.registerKeys();

        ClientTickEvents.END_CLIENT_TICK.register(ModClientEvents::onEndTick);
        HudElementRegistry.addFirst(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "mana_display"), ModClientEvents::addFirstHudElement);
    }
}
