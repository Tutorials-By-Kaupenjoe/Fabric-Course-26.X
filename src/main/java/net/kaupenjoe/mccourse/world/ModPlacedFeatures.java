package net.kaupenjoe.mccourse.world;

import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> BISMUTH_OVERWORLD_ORES_PLACED_KEY = registerKey("bismuth_overworld_ores_placed");
    public static final ResourceKey<PlacedFeature> BISMUTH_NETHER_ORES_PLACED_KEY = registerKey("bismuth_nether_ores_placed");
    public static final ResourceKey<PlacedFeature> BISMUTH_END_ORES_PLACED_KEY = registerKey("bismuth_end_ores_placed");

    public static final ResourceKey<PlacedFeature> BLOODWOOD_TREE_PLACED_KEY = registerKey("bloodwood_tree_placed_key");

    public static final ResourceKey<PlacedFeature> PETUNIA_FLOWER_PLACED_KEY = registerKey("petunia_flower_placed");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, BISMUTH_OVERWORLD_ORES_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BISMUTH_OVERWORLD_ORES_KEY),
                ModOrePlacements.commonOrePlacement(14,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));
        register(context, BISMUTH_NETHER_ORES_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BISMUTH_NETHER_ORES_KEY),
                ModOrePlacements.commonOrePlacement(14,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));
        register(context, BISMUTH_END_ORES_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BISMUTH_END_ORES_KEY),
                ModOrePlacements.commonOrePlacement(14,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));

        register(context, BLOODWOOD_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOODWOOD_TREE_KEY),
                VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(2, 0.1f, 2), ModBlocks.BLOODWOOD_SAPLING));

        register(context, PETUNIA_FLOWER_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PETUNIA_FLOWER_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));


    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                                                                          Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
