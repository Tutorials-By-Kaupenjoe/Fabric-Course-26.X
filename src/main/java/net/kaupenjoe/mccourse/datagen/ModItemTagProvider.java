package net.kaupenjoe.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.getRK(ModItems.BISMUTH))
                .add(ItemIds.IRON_INGOT)
                .add(ItemIds.COAL)
                .add(ModItems.getRK(ModItems.CAULIFLOWER));

        tag(ModTags.Items.BISMUTH_REPAIRABLE)
                .add(ModItems.getRK(ModItems.BISMUTH));

        tag(ItemTags.SWORDS).add(ModItems.getRK(ModItems.BISMUTH_SWORD));
        tag(ItemTags.PICKAXES).add(ModItems.getRK(ModItems.BISMUTH_PICKAXE)).add(ModItems.getRK(ModItems.BISMUTH_PAXEL)).add(ModItems.getRK(ModItems.BISMUTH_HAMMER));
        tag(ItemTags.SHOVELS).add(ModItems.getRK(ModItems.BISMUTH_SHOVEL)).add(ModItems.getRK(ModItems.BISMUTH_PAXEL));
        tag(ItemTags.AXES).add(ModItems.getRK(ModItems.BISMUTH_AXE)).add(ModItems.getRK(ModItems.BISMUTH_PAXEL));
        tag(ItemTags.HOES).add(ModItems.getRK(ModItems.BISMUTH_HOE));

        tag(ItemTags.HEAD_ARMOR)
                .add(ModItems.getRK(ModItems.BISMUTH_HELMET));
        tag(ItemTags.CHEST_ARMOR)
                .add(ModItems.getRK(ModItems.BISMUTH_CHESTPLATE));
        tag(ItemTags.LEG_ARMOR)
                .add(ModItems.getRK(ModItems.BISMUTH_LEGGINGS));
        tag(ItemTags.FOOT_ARMOR)
                .add(ModItems.getRK(ModItems.BISMUTH_BOOTS));

        tag(ItemTags.BOW_ENCHANTABLE)
                .add(ModItems.getRK(ModItems.KAUPEN_BOW));

        tag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ModItems.getRK(ModItems.CAULIFLOWER_SEEDS));

        tag(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(ModItems.getRK(ModItems.BAR_BRAWL_MUSIC_DISC));

        tag(ModTags.Items.BLOODWOOD_LOGS)
                .add(ModItems.getRK(ModBlocks.BLOODWOOD_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.BLOODWOOD_WOOD.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_BLOODWOOD_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_BLOODWOOD_WOOD.asItem()));

        tag(ItemTags.PLANKS)
                .add(ModItems.getRK(ModBlocks.BLOODWOOD_PLANKS.asItem()));

        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModItems.getRK(ModBlocks.BLOODWOOD_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.BLOODWOOD_WOOD.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_BLOODWOOD_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_BLOODWOOD_WOOD.asItem()));
    }
}
