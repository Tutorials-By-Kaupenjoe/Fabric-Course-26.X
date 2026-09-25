package net.kaupenjoe.mccourse.world;

import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.world.tree.InvertedPyramidFoliagePlacer;
import net.kaupenjoe.mccourse.world.tree.SpiralTrunkPlacer;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.OffsetPlacement;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModFeatures {
    public static final ResourceKey<Feature> BISMUTH_OVERWORLD_ORES_KEY = registerKey("bismuth_overworld_ores");
    public static final ResourceKey<Feature> BISMUTH_NETHER_ORES_KEY = registerKey("bismuth_nether_ores");
    public static final ResourceKey<Feature> BISMUTH_END_ORES_KEY = registerKey("bismuth_end_ores");

    public static final ResourceKey<Feature> BLOODWOOD_TREE_KEY = registerKey("bloodwood_tree_key");

    public static final ResourceKey<Feature> PETUNIA_FLOWER_KEY = registerKey("petunia_flower");

    public static final ResourceKey<Feature> HONEY_BERRY_BUSH_KEY = registerKey("honey_berry_bush");

    public static final ResourceKey<Feature> BISMUTH_GEODE_KEY = registerKey("bismuth_geode");


    public static void bootstrap(BootstrapContext<Feature> context) {
        var blocks = context.lookup(Registries.BLOCK);

        List<BlockReplacement> overworldBismuthTargets = List.of(
                BlockReplacement.replace(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.BISMUTH_ORE.defaultBlockState()),
                BlockReplacement.replace(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.BISMUTH_DEEPSLATE_ORE.defaultBlockState()));
        List<BlockReplacement> netherBismuthTargets = List.of(
                BlockReplacement.replace(new TagMatchTest(BlockTags.BASE_STONE_NETHER), ModBlocks.BISMUTH_NETHER_ORE.defaultBlockState()));
        List<BlockReplacement> endBismuthTargets = List.of(
                BlockReplacement.replace(new BlockMatchTest(Blocks.END_STONE), ModBlocks.BISMUTH_END_ORE.defaultBlockState()));

        context.register(BISMUTH_OVERWORLD_ORES_KEY, new OreFeature(overworldBismuthTargets, 12));
        context.register(BISMUTH_NETHER_ORES_KEY, new OreFeature(netherBismuthTargets, 9));
        context.register(BISMUTH_END_ORES_KEY, new OreFeature(endBismuthTargets, 12));

        context.register(BLOODWOOD_TREE_KEY, new TreeFeature.Builder(
                BlockStateProvider.of(ModBlocks.BLOODWOOD_LOG),
                new SpiralTrunkPlacer(4, 2, 4),

                BlockStateProvider.of(ModBlocks.BLOODWOOD_LEAVES),
                new InvertedPyramidFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), 3),

                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.holderOf(Blocks.STONE))
                .build());

        context.register(PETUNIA_FLOWER_KEY, new SimpleRandomSelectorFeature(
                        HolderSet.direct(PlacementUtils.inlinePlaced(
                                new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.PETUNIA)),
                                CountPlacement.of(32),
                                OffsetPlacement.ofTriangle(6, 3),
                                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
                        ))));

        context.register(HONEY_BERRY_BUSH_KEY, new SimpleRandomSelectorFeature(
                        HolderSet.direct(PlacementUtils.inlinePlaced(
                                new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.HONEY_BERRY_BUSH
                                        .defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))),
                                CountPlacement.of(67),
                                OffsetPlacement.ofTriangle(6, 3),
                                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
                        ))));

        context.register(BISMUTH_GEODE_KEY, new GeodeFeature(new GeodeBlockSettings(
                        BlockStateProvider.holderOf(Blocks.AIR),
                        BlockStateProvider.holderOf(Blocks.DEEPSLATE),
                        BlockStateProvider.holderOf(ModBlocks.BISMUTH_BLOCK),
                        BlockStateProvider.holderOf(Blocks.DIRT),
                        BlockStateProvider.holderOf(Blocks.EMERALD_BLOCK),
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

    public static ResourceKey<Feature> registerKey(String name) {
        return ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name));
    }
}
