package net.kaupenjoe.mccourse;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.kaupenjoe.mccourse.event.ModClientEvents;
import net.kaupenjoe.mccourse.keybind.ModKeyMappings;

public class MCCourseClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeyMappings.registerKeys();

        ClientTickEvents.END_CLIENT_TICK.register(ModClientEvents::onEndTick);
    }
}
