package net.kaupenjoe.mccourse.registries;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.kaupenjoe.mccourse.block.ModBlocks;

public class ModStrippableBlocks {
    public static void registerStrippableBlocks() {
        StrippableBlockRegistry.register(ModBlocks.BLOODWOOD_LOG, ModBlocks.STRIPPED_BLOODWOOD_LOG);
        StrippableBlockRegistry.register(ModBlocks.BLOODWOOD_WOOD, ModBlocks.STRIPPED_BLOODWOOD_WOOD);
    }
}
