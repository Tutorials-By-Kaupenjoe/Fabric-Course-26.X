package net.kaupenjoe.mccourse.world.tree;

import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class ModFoliagePlacerTypes {
    public static final FoliagePlacerType<InvertedPyramidFoliagePlacer> INVERTED_PYRAMID_FOLIAGE_PLACER =
            Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID,
                    "inverted_pyramid_foliage_placer"), new FoliagePlacerType<>(InvertedPyramidFoliagePlacer.CODEC));

    public static void registerModFoliagePlacerTypes() {
        MCCourse.LOGGER.info("Registering Foliage Placers for " + MCCourse.MOD_ID);
    }
}
