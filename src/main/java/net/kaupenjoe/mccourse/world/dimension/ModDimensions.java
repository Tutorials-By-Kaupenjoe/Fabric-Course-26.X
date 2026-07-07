package net.kaupenjoe.mccourse.world.dimension;

import com.mojang.datafixers.util.Pair;
import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TimelineTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.attribute.AmbientSounds;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.Optional;

public class ModDimensions {
    public static final ResourceKey<LevelStem> KAUPENDIM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "kaupendim"));
    public static final ResourceKey<Level> KAUPENDIM_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "kaupendim"));
    public static final ResourceKey<DimensionType> KAUPENDIM_TYPE_KEY = ResourceKey.create(Registries.DIMENSION_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "kaupendim_type"));

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        var timelines = context.lookup(Registries.TIMELINE);
        var clocks = context.lookup(Registries.WORLD_CLOCK);
        var blocks = context.lookup(Registries.BLOCK);

        context.register(KAUPENDIM_TYPE_KEY, new DimensionType(
                false,
                false,
                false,
                false,
                1.0,
                0,
                256,
                256,
                blocks.getOrThrow(BlockTags.INFINIBURN_OVERWORLD),
                1.0f,
                new DimensionType.MonsterSettings(ConstantInt.of(0), 0),
                DimensionType.Skybox.OVERWORLD,
                CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.builder()
                        .set(EnvironmentAttributes.STAR_BRIGHTNESS, 0.9f)
                        .set(EnvironmentAttributes.FOG_COLOR, -6168945)
                        .set(EnvironmentAttributes.SKY_COLOR, OverworldBiomes.calculateSkyColor(1.95f))
                        .set(EnvironmentAttributes.CLOUD_COLOR, 0xA14313ba)
                        .set(EnvironmentAttributes.BACKGROUND_MUSIC, BackgroundMusic.OVERWORLD)
                        .set(EnvironmentAttributes.AMBIENT_SOUNDS, AmbientSounds.LEGACY_CAVE_SETTINGS)
                        .set(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, -4129286)
                        .build(),
                timelines.getOrThrow(TimelineTags.IN_OVERWORLD),
                Optional.of(clocks.getOrThrow(WorldClocks.OVERWORLD))));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        var biomes = context.lookup(Registries.BIOME);
        var dimensionTypes = context.lookup(Registries.DIMENSION_TYPE);
        var noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator fixedBiome = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomes.getOrThrow(Biomes.CHERRY_GROVE)),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.FLOATING_ISLANDS));

        NoiseBasedChunkGenerator multiBiomes = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(0f, 0f, 0f, 0f, 0f, 0f, 0f), biomes.getOrThrow(Biomes.CHERRY_GROVE)),
                                Pair.of(Climate.parameters(0.2f, 0.1f, -0.2f, 0.1f, 0f, 0f, 0f), biomes.getOrThrow(Biomes.BEACH)),
                                Pair.of(Climate.parameters(0.25f, 0.2f, -0.4f, 0f, 0f, 0f, 0f), biomes.getOrThrow(Biomes.DEEP_LUKEWARM_OCEAN)),
                                Pair.of(Climate.parameters(-0.1f, -0.1f, 0.25f, 1.25f, 0f, 0f, 0f), biomes.getOrThrow(Biomes.STONY_PEAKS))
                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));

        LevelStem stem = new LevelStem(dimensionTypes.getOrThrow(ModDimensions.KAUPENDIM_TYPE_KEY), multiBiomes);
        context.register(KAUPENDIM_KEY, stem);
    }
}
