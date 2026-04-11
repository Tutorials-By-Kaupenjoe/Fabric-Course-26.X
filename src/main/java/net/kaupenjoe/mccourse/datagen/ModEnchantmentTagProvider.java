package net.kaupenjoe.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.kaupenjoe.mccourse.enchantment.ModEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagProvider extends FabricTagsProvider<Enchantment> {
    public ModEnchantmentTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.ENCHANTMENT, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        getOrCreateRawBuilder(EnchantmentTags.IN_ENCHANTING_TABLE)
                .add(TagEntry.element(ModEnchantments.LIGHTNING_STRIKER.identifier()));
    }
}
