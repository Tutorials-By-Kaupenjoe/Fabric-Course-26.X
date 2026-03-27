package net.kaupenjoe.mccourse.block;

import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

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
