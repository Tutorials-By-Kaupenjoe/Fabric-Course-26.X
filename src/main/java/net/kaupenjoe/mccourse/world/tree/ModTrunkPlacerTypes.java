package net.kaupenjoe.mccourse.world.tree;

import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class ModTrunkPlacerTypes {
    public static final TrunkPlacerType<SpiralTrunkPlacer> SPIRAL_TRUNK_PLACER =
            Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID,
                    "spiral_trunk_placer"), new TrunkPlacerType<>(SpiralTrunkPlacer.CODEC));

    public static void registerModTrunkPlacerTypes() {
        MCCourse.LOGGER.info("Registering Trunk Placer Types for " + MCCourse.MOD_ID);
    }
}
