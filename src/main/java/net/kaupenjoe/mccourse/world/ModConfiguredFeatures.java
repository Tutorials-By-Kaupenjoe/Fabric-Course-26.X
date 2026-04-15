package net.kaupenjoe.mccourse.world;

import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> BISMUTH_OVERWORLD_ORES_KEY = registerKey("bismuth_overworld_ores");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BISMUTH_NETHER_ORES_KEY = registerKey("bismuth_nether_ores");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BISMUTH_END_ORES_KEY = registerKey("bismuth_end_ores");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOODWOOD_TREE_KEY = registerKey("bloodwood_tree_key");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
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
                new BendingTrunkPlacer(4, 2, 4, 5, ConstantInt.of(4)),

                BlockStateProvider.simple(ModBlocks.BLOODWOOD_LEAVES),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3)),

                new TwoLayersFeatureSize(1, 0, 2))
                .belowTrunkProvider(BlockStateProvider.simple(Blocks.STONE))
                .build());

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
