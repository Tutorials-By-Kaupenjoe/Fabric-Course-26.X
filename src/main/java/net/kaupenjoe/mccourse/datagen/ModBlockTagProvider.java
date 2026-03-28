package net.kaupenjoe.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BISMUTH_BLOCK)
                .add(ModBlocks.RAW_BISMUTH_BLOCK)
                .add(ModBlocks.BISMUTH_ORE)
                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE)
                .add(ModBlocks.BISMUTH_NETHER_ORE)
                .add(ModBlocks.BISMUTH_END_ORE)
                .add(ModBlocks.MAGIC_BLOCK)
                .add(ModBlocks.BISMUTH_STAIRS)
                .add(ModBlocks.BISMUTH_SLAB)
                .add(ModBlocks.BISMUTH_FENCE)
                .add(ModBlocks.BISMUTH_FENCE_GATE)
                .add(ModBlocks.BISMUTH_WALL);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.BISMUTH_END_ORE);

        valueLookupBuilder(BlockTags.STAIRS)
                .add(ModBlocks.BISMUTH_STAIRS);
        valueLookupBuilder(BlockTags.SLABS)
                .add(ModBlocks.BISMUTH_SLAB);

        valueLookupBuilder(BlockTags.BUTTONS)
                .add(ModBlocks.BISMUTH_BUTTON);
        valueLookupBuilder(BlockTags.PRESSURE_PLATES)
                .add(ModBlocks.BISMUTH_PRESSURE_PLATE);

        valueLookupBuilder(BlockTags.FENCES)
                .add(ModBlocks.BISMUTH_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.BISMUTH_FENCE);
        valueLookupBuilder(BlockTags.WALLS)
                .add(ModBlocks.BISMUTH_WALL);
    }
}
