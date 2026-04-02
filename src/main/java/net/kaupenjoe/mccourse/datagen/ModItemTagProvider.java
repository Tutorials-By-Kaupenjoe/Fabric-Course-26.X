package net.kaupenjoe.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.BISMUTH)
                .add(Items.IRON_INGOT)
                .add(Items.COAL)
                .add(ModItems.CAULIFLOWER);

        valueLookupBuilder(ModTags.Items.BISMUTH_REPAIRABLE)
                .add(ModItems.BISMUTH);

        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.BISMUTH_SWORD);
        valueLookupBuilder(ItemTags.PICKAXES).add(ModItems.BISMUTH_PICKAXE);
        valueLookupBuilder(ItemTags.SHOVELS).add(ModItems.BISMUTH_SHOVEL);
        valueLookupBuilder(ItemTags.AXES).add(ModItems.BISMUTH_AXE);
        valueLookupBuilder(ItemTags.HOES).add(ModItems.BISMUTH_HOE);


    }
}
