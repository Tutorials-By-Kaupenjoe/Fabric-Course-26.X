package net.kaupenjoe.mccourse.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.kaupenjoe.mccourse.world.ModPlacedFeatures;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModTreeGeneration {
    public static void generateTrees() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PLAINS, Biomes.MEADOW),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BLOODWOOD_TREE_PLACED_KEY);
    }
}
