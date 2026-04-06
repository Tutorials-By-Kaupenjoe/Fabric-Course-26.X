package net.kaupenjoe.mccourse.registries;

import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.kaupenjoe.mccourse.item.ModItems;

public class ModCompostables {
    public static void registerCompostables() {
        CompostableRegistry.INSTANCE.add(ModItems.CAULIFLOWER, 0.65f);
        CompostableRegistry.INSTANCE.add(ModItems.CAULIFLOWER_SEEDS, 0.3f);
    }
}
