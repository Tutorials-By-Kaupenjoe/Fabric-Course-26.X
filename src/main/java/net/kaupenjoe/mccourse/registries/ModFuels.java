package net.kaupenjoe.mccourse.registries;

import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.kaupenjoe.mccourse.item.ModItems;

public class ModFuels {
    public static void registerModFuels() {
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.STARLIGHT_ASHES, 800);
        });
    }
}
