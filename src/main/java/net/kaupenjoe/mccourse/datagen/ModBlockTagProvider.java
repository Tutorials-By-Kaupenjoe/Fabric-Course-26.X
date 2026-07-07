package net.kaupenjoe.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BISMUTH_BLOCK.properties().blockIdOrThrow())
                .add(ModBlocks.RAW_BISMUTH_BLOCK.properties().blockIdOrThrow())
                .add(ModBlocks.BISMUTH_ORE.properties().blockIdOrThrow())
                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE.properties().blockIdOrThrow())
                .add(ModBlocks.BISMUTH_NETHER_ORE.properties().blockIdOrThrow())
                .add(ModBlocks.BISMUTH_END_ORE.properties().blockIdOrThrow())
                .add(ModBlocks.BISMUTH_STAIRS.properties().blockIdOrThrow())
                .add(ModBlocks.BISMUTH_SLAB.properties().blockIdOrThrow())
                .add(ModBlocks.BISMUTH_FENCE.properties().blockIdOrThrow())
                .add(ModBlocks.BISMUTH_FENCE_GATE.properties().blockIdOrThrow())
                .add(ModBlocks.BISMUTH_WALL.properties().blockIdOrThrow())
                .add(ModBlocks.BISMUTH_DOOR.properties().blockIdOrThrow())
                .add(ModBlocks.BISMUTH_TRAPDOOR.properties().blockIdOrThrow())
                .add(ModBlocks.BISMUTH_LAMP.properties().blockIdOrThrow());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE.properties().blockIdOrThrow());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.BISMUTH_END_ORE.properties().blockIdOrThrow());

        tag(BlockTags.STAIRS)
                .add(ModBlocks.BISMUTH_STAIRS.properties().blockIdOrThrow());
        tag(BlockTags.SLABS)
                .add(ModBlocks.BISMUTH_SLAB.properties().blockIdOrThrow());

        tag(BlockTags.BUTTONS)
                .add(ModBlocks.BISMUTH_BUTTON.properties().blockIdOrThrow());
        tag(BlockTags.PRESSURE_PLATES)
                .add(ModBlocks.BISMUTH_PRESSURE_PLATE.properties().blockIdOrThrow());

        tag(BlockTags.FENCES)
                .add(ModBlocks.BISMUTH_FENCE.properties().blockIdOrThrow());
        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.BISMUTH_FENCE.properties().blockIdOrThrow());
        tag(BlockTags.WALLS)
                .add(ModBlocks.BISMUTH_WALL.properties().blockIdOrThrow());

        tag(BlockTags.DOORS)
                .add(ModBlocks.BISMUTH_DOOR.properties().blockIdOrThrow());
        tag(BlockTags.TRAPDOORS)
                .add(ModBlocks.BISMUTH_TRAPDOOR.properties().blockIdOrThrow());

        tag(ModTags.Blocks.NEEDS_BISMUTH_TOOL)
                .add(ModBlocks.MAGIC_BLOCK.properties().blockIdOrThrow())
                .addTag(BlockTags.NEEDS_IRON_TOOL);
        tag(ModTags.Blocks.INCORRECT_FOR_BISMUTH_TOOL)
                .addOptionalTag(BlockTags.INCORRECT_FOR_IRON_TOOL);

        tag(ModTags.Blocks.PAXEL_MINEABLE)
                .forceAddTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .forceAddTag(BlockTags.MINEABLE_WITH_SHOVEL)
                .forceAddTag(BlockTags.MINEABLE_WITH_AXE);

        tag(BlockTags.CROPS)
                .add(ModBlocks.CAULIFLOWER_CROP.properties().blockIdOrThrow())
                .add(ModBlocks.RICE_CROP.properties().blockIdOrThrow());
        tag(BlockTags.FLOWERS)
                .add(ModBlocks.PETUNIA.properties().blockIdOrThrow());
        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_PETUNIA.properties().blockIdOrThrow());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.BLOODWOOD_PLANKS.properties().blockIdOrThrow());

        tag(ModTags.Blocks.BLOODWOOD_LOGS)
                .add(ModBlocks.BLOODWOOD_LOG.properties().blockIdOrThrow())
                .add(ModBlocks.BLOODWOOD_WOOD.properties().blockIdOrThrow())
                .add(ModBlocks.STRIPPED_BLOODWOOD_LOG.properties().blockIdOrThrow())
                .add(ModBlocks.STRIPPED_BLOODWOOD_WOOD.properties().blockIdOrThrow());

    }
}
