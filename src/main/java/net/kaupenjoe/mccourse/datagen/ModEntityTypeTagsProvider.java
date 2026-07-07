package net.kaupenjoe.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.kaupenjoe.mccourse.entity.ModEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagsProvider extends FabricTagsProvider.EntityTypeTagsProvider {
    public ModEntityTypeTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(EntityTypeTags.CAN_EQUIP_SADDLE)
                .add(ModEntities.DODO_KEY);
    }
}
