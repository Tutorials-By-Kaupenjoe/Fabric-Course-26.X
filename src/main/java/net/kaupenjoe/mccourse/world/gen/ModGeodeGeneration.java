package net.kaupenjoe.mccourse.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.kaupenjoe.mccourse.world.ModPlacedFeatures;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModGeodeGeneration {
    public static void generateGeodes() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.LOCAL_MODIFICATIONS, ModPlacedFeatures.BISMUTH_GEODE_PLACED_KEY);
    }
}
