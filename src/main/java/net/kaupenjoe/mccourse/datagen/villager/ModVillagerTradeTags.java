package net.kaupenjoe.mccourse.datagen.villager;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.kaupenjoe.mccourse.tag.ModTags;
import net.kaupenjoe.mccourse.villager.ModVillagers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.concurrent.CompletableFuture;

public class ModVillagerTradeTags extends FabricTagsProvider<VillagerTrade> {
    public ModVillagerTradeTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.VILLAGER_TRADE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        getOrCreateRawBuilder(VillagerTradeTags.FARMER_LEVEL_1)
                .add(TagEntry.element(ModVillagerTrades.FARMER_1_EMERALD_CAULIFLOWER.identifier()))
                .add(TagEntry.element(ModVillagerTrades.FARMER_1_DIAMOND_CAULIFLOWER_SEEDS.identifier()));
        getOrCreateRawBuilder(VillagerTradeTags.FARMER_LEVEL_2)
                .add(TagEntry.element(ModVillagerTrades.FARMER_2_EMERALD_HONEY_BERRIES.identifier()));

        getOrCreateRawBuilder(VillagerTradeTags.MASON_LEVEL_1)
                .add(TagEntry.element(ModVillagerTrades.MASON_1_EMERALD_CHISEL.identifier()));

        getOrCreateRawBuilder(ModTags.Trades.KAUPENGER_LEVEL_1)
                .add(TagEntry.element(ModVillagerTrades.KAUPENGER_1_EMERALD_BISMUTH.identifier()))
                .add(TagEntry.element(ModVillagerTrades.KAUPENGER_1_EMERALD_RAW_BISMUTH.identifier()));
        getOrCreateRawBuilder(ModTags.Trades.KAUPENGER_LEVEL_2)
                .add(TagEntry.element(ModVillagerTrades.KAUPENGER_2_EMERALD_CHAIR.identifier()))
                .add(TagEntry.element(ModVillagerTrades.KAUPENGER_2_BISMUTH_SPECTRE_STAFF.identifier()));
    }
}
