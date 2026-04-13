package net.kaupenjoe.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.level.material.Fluid;

import java.util.concurrent.CompletableFuture;

public class ModFluidTags extends FabricTagsProvider<Fluid> {
    public ModFluidTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.FLUID, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        getOrCreateRawBuilder(FluidTags.WATER)
                .add(TagEntry.element(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "bismuth_water_source")))
                .add(TagEntry.element(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "bismuth_water_flowing")));
    }
}
