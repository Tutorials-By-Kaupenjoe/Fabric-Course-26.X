package net.kaupenjoe.mccourse.world;

import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.world.tree.InvertedPyramidFoliagePlacer;
import net.kaupenjoe.mccourse.world.tree.SpiralTrunkPlacer;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> BISMUTH_OVERWORLD_ORES_KEY = registerKey("bismuth_overworld_ores");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BISMUTH_NETHER_ORES_KEY = registerKey("bismuth_nether_ores");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BISMUTH_END_ORES_KEY = registerKey("bismuth_end_ores");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOODWOOD_TREE_KEY = registerKey("bloodwood_tree_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PETUNIA_FLOWER_KEY = registerKey("petunia_flower");

    public static final ResourceKey<ConfiguredFeature<?, ?>> HONEY_BERRY_BUSH_KEY = registerKey("honey_berry_bush");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BISMUTH_GEODE_KEY = registerKey("bismuth_geode");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        var blocks = context.lookup(Registries.BLOCK);

        List<OreConfiguration.TargetBlockState> overworldBismuthTargets = List.of(
                        OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.BISMUTH_ORE.defaultBlockState()),
                        OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.BISMUTH_DEEPSLATE_ORE.defaultBlockState()));
        List<OreConfiguration.TargetBlockState> netherBismuthTargets = List.of(
                        OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_NETHER), ModBlocks.BISMUTH_NETHER_ORE.defaultBlockState()));
        List<OreConfiguration.TargetBlockState> endBismuthTargets = List.of(
                        OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), ModBlocks.BISMUTH_END_ORE.defaultBlockState()));

        register(context, BISMUTH_OVERWORLD_ORES_KEY, Feature.ORE, new OreConfiguration(overworldBismuthTargets, 12));
        register(context, BISMUTH_NETHER_ORES_KEY, Feature.ORE, new OreConfiguration(netherBismuthTargets, 9));
        register(context, BISMUTH_END_ORES_KEY, Feature.ORE, new OreConfiguration(endBismuthTargets, 12));

        register(context, BLOODWOOD_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.BLOODWOOD_LOG),
                new SpiralTrunkPlacer(4, 2, 4),

                BlockStateProvider.simple(ModBlocks.BLOODWOOD_LEAVES),
                new InvertedPyramidFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), 3),

                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.simple(Blocks.STONE))
                .build());

        register(context, PETUNIA_FLOWER_KEY, Feature.SIMPLE_RANDOM_SELECTOR,
                new CompositeFeatureConfiguration(
                        HolderSet.direct(PlacementUtils.inlinePlaced(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PETUNIA)),
                                CountPlacement.of(32),
                                RandomOffsetPlacement.ofTriangle(6, 3),
                                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
                        ))));

        register(context, HONEY_BERRY_BUSH_KEY, Feature.SIMPLE_RANDOM_SELECTOR,
                new CompositeFeatureConfiguration(
                        HolderSet.direct(PlacementUtils.inlinePlaced(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.HONEY_BERRY_BUSH
                                        .defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))),
                                CountPlacement.of(67),
                                RandomOffsetPlacement.ofTriangle(6, 3),
                                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
                        ))));

        register(context, BISMUTH_GEODE_KEY, Feature.GEODE,
                new GeodeConfiguration(new GeodeBlockSettings(
                                BlockStateProvider.simple(Blocks.AIR),
                                BlockStateProvider.simple(Blocks.DEEPSLATE),
                                BlockStateProvider.simple(ModBlocks.BISMUTH_BLOCK),
                                BlockStateProvider.simple(Blocks.DIRT),
                                BlockStateProvider.simple(Blocks.EMERALD_BLOCK),
                                List.of(
                                        ModBlocks.RAW_BISMUTH_BLOCK.defaultBlockState(),
                                        ModBlocks.MAGIC_BLOCK.defaultBlockState()
                                ),
                                blocks.getOrThrow(BlockTags.FEATURES_CANNOT_REPLACE),
                                blocks.getOrThrow(BlockTags.GEODE_INVALID_BLOCKS)
                        ),
                        new GeodeLayerSettings(1.7, 2.2, 3.2, 4.2),
                        new GeodeCrackSettings(0.95, 2.0, 2),
                        0.35,
                        0.083,
                        true,
                        UniformInt.of(4, 6),
                        UniformInt.of(3, 4),
                        UniformInt.of(1, 2),
                        -16,
                        16,
                        0.05,
                        1));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
