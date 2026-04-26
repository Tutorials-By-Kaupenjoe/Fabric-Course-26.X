package net.kaupenjoe.mccourse.block;

import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.block.custom.*;
import net.kaupenjoe.mccourse.fluid.ModFluids;
import net.kaupenjoe.mccourse.sound.ModSounds;
import net.kaupenjoe.mccourse.world.tree.ModTreeGrowers;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ModBlocks {
    public static final Block BISMUTH_BLOCK = registerBlock("bismuth_block",
            properties -> new Block(properties.strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
        public static final Block RAW_BISMUTH_BLOCK = registerBlock("raw_bismuth_block",
            properties -> new Block(properties.strength(3f)
                    .requiresCorrectToolForDrops()));

        public static final Block BISMUTH_ORE = registerBlock("bismuth_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4),
                    properties.strength(3f).requiresCorrectToolForDrops()));
        public static final Block BISMUTH_DEEPSLATE_ORE = registerBlock("bismuth_deepslate_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3, 5),
                    properties.strength(4f).requiresCorrectToolForDrops()));
        public static final Block BISMUTH_NETHER_ORE = registerBlock("bismuth_nether_ore",
            properties -> new DropExperienceBlock(UniformInt.of(5, 6),
                    properties.strength(3f).requiresCorrectToolForDrops()));
        public static final Block BISMUTH_END_ORE = registerBlock("bismuth_end_ore",
            properties -> new DropExperienceBlock(UniformInt.of(6, 7),
                    properties.strength(4f).requiresCorrectToolForDrops()));

    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            properties -> new MagicBlock(properties.strength(2f)
                    .requiresCorrectToolForDrops().sound(ModSounds.MAGIC_BLOCK_SOUNDS)));

    public static final Block BISMUTH_STAIRS = registerBlock("bismuth_stairs",
            properties -> new StairBlock(ModBlocks.BISMUTH_BLOCK.defaultBlockState(),
                    properties.strength(2f).requiresCorrectToolForDrops()));
    public static final Block BISMUTH_SLAB = registerBlock("bismuth_slab",
            properties -> new SlabBlock(properties.strength(2f).requiresCorrectToolForDrops()));

    public static final Block BISMUTH_BUTTON = registerBlock("bismuth_button",
            properties -> new ButtonBlock(BlockSetType.IRON, 20, properties
                    .noCollision().strength(0.5F).pushReaction(PushReaction.DESTROY)));
    public static final Block BISMUTH_PRESSURE_PLATE = registerBlock("bismuth_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.IRON, properties
                    .mapColor(MapColor.STONE).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).noCollision()
                    .strength(0.5F).pushReaction(PushReaction.DESTROY)));

    public static final Block BISMUTH_FENCE = registerBlock("bismuth_fence",
            properties -> new FenceBlock(properties.strength(2f).requiresCorrectToolForDrops()));
    public static final Block BISMUTH_FENCE_GATE = registerBlock("bismuth_fence_gate",
            properties -> new FenceGateBlock(WoodType.ACACIA, properties.forceSolidOn()
                    .strength(2f).requiresCorrectToolForDrops()));
    public static final Block BISMUTH_WALL = registerBlock("bismuth_wall",
            properties -> new WallBlock(properties.strength(2f).requiresCorrectToolForDrops()));

    public static final Block BISMUTH_DOOR = registerBlock("bismuth_door",
            properties -> new DoorBlock(BlockSetType.IRON,
                    properties.strength(2f).noOcclusion().requiresCorrectToolForDrops()));
    public static final Block BISMUTH_TRAPDOOR = registerBlock("bismuth_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.IRON,
                    properties.strength(2f).noOcclusion().requiresCorrectToolForDrops()));

    public static final Block BISMUTH_LAMP = registerBlock("bismuth_lamp",
            properties -> new BismuthLampBlock(properties.requiresCorrectToolForDrops()
                    .strength(0.5F).lightLevel(state -> state.getValue(BismuthLampBlock.CLICKED) ? 15 : 0)));

    public static final Block CAULIFLOWER_CROP = registerBlockWithoutBlockItem("cauliflower_crop",
            properties -> new CauliflowerCropBlock(properties.randomTicks().instabreak()
                    .sound(SoundType.CROP).noCollision().pushReaction(PushReaction.DESTROY)));

    public static final Block PETUNIA = registerBlock("petunia",
            properties -> new FlowerBlock(MobEffects.BLINDNESS, 12, properties.instabreak().mapColor(MapColor.PLANT)
                    .sound(SoundType.GRASS).noCollision().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
    public static final Block POTTED_PETUNIA = registerBlockWithoutBlockItem("potted_petunia",
            properties -> new FlowerPotBlock(PETUNIA, properties.instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final Block COLORED_LEAVES = registerBlock("colored_leaves",
            properties -> new TintedParticleLeavesBlock(0.1f, properties.mapColor(MapColor.PLANT)
                    .strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(Blocks::ocelotOrParrot)
                    .isSuffocating(Blocks::never).isViewBlocking(Blocks::never)
                    .ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(Blocks::never)));

    public static final Block HONEY_BERRY_BUSH = registerBlockWithoutBlockItem("honey_berry_bush",
            properties -> new HoneyBerryBushBlock(properties.mapColor(MapColor.COLOR_GREEN).randomTicks()
                    .noCollision().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));

    public static final Block RICE_CROP = registerBlockWithoutBlockItem("rice_crop",
            properties -> new RiceCropBlock(properties.randomTicks().instabreak()
                    .sound(SoundType.CROP).noCollision().pushReaction(PushReaction.DESTROY)));

    public static final Block CHAIR = registerBlock("chair",
            properties -> new ChairBlock(properties.mapColor(MapColor.WOOD).strength(1.25f)
                    .sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)));

    public static final Block BISMUTH_WATER_BLOCK = registerBlockWithoutBlockItem("bismuth_water_block",
            properties -> new LiquidBlock(ModFluids.BISMUTH_WATER_SOURCE, properties
                    .mapColor(MapColor.WATER).replaceable().noCollision().strength(100.0F)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));

    public static final Block BLOODWOOD_LOG = registerBlock("bloodwood_log",
            properties -> new RotatedPillarBlock(properties.mapColor(MapColor.WOOD).strength(2f)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block BLOODWOOD_WOOD = registerBlock("bloodwood_wood",
            properties -> new RotatedPillarBlock(properties.mapColor(MapColor.WOOD).strength(2f)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_BLOODWOOD_LOG = registerBlock("stripped_bloodwood_log",
            properties -> new RotatedPillarBlock(properties.mapColor(MapColor.WOOD).strength(2f)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_BLOODWOOD_WOOD = registerBlock("stripped_bloodwood_wood",
            properties -> new RotatedPillarBlock(properties.mapColor(MapColor.WOOD).strength(2f)
                    .sound(SoundType.WOOD).ignitedByLava()));

    public static final Block BLOODWOOD_PLANKS = registerBlock("bloodwood_planks",
            properties -> new Block(properties.mapColor(MapColor.WOOD).strength(2f)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block BLOODWOOD_LEAVES = registerBlock("bloodwood_leaves",
            properties -> new UntintedParticleLeavesBlock(0f, ParticleTypes.CHERRY_LEAVES,
                    properties.mapColor(MapColor.METAL).strength(0.2F).randomTicks().sound(SoundType.GRASS)
                            .noOcclusion().isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never)
                            .isViewBlocking(Blocks::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
                            .isRedstoneConductor(Blocks::never)));


    public static final Block BLOODWOOD_SAPLING = registerBlock("bloodwood_sapling",
            properties -> new ModSaplingBlock(ModTreeGrowers.BLOODWOOD, properties.mapColor(MapColor.GRASS).noCollision()
                    .randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY), Blocks.STONE));
    public static final Block POTTED_BLOODWOOD_SAPLING = registerBlockWithoutBlockItem("potted_bloodwood_sapling",
            properties -> new FlowerPotBlock(BLOODWOOD_SAPLING, properties.mapColor(MapColor.GRASS).noOcclusion()
                    .instabreak().pushReaction(PushReaction.DESTROY)));


    public static final Block KAUPEN_PORTAL = registerBlock("kaupen_portal",
            properties -> new KaupenPortalBlock(properties.strength(1f)));

    public static final Block MAIN_PEDESTAL = registerBlock("main_pedestal",
            properties -> new MainPedestalBlock(properties.strength(2f).noOcclusion()));
    public static final Block SIDE_PEDESTAL = registerBlock("side_pedestal",
            properties -> new SidePedestalBlock(properties.strength(2f).noOcclusion()));
    public static final Block CRYSTALLIZER = registerBlock("crystallizer",
            properties -> new CrystallizerBlock(properties.strength(2f)));



    private static Block registerBlockWithoutBlockItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name), function.apply(BlockBehaviour.Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name)))));
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        MCCourse.LOGGER.info("Registering Mod Blocks for " + MCCourse.MOD_ID);
    }
}
