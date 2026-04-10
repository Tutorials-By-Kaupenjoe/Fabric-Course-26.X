package net.kaupenjoe.mccourse.datagen.villager;

import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.List;
import java.util.Optional;

public class ModVillagerTrades {
    public static final ResourceKey<VillagerTrade> FARMER_1_EMERALD_CAULIFLOWER = createKey("farmer/1/emerald_cauliflower");
    public static final ResourceKey<VillagerTrade> FARMER_1_DIAMOND_CAULIFLOWER_SEEDS = createKey("farmer/1/diamond_cauliflower_seeds");

    public static final ResourceKey<VillagerTrade> FARMER_2_EMERALD_HONEY_BERRIES = createKey("farmer/2/emerald_honey_berries");

    public static final ResourceKey<VillagerTrade> MASON_1_EMERALD_CHISEL = createKey("mason/1/emerald_chisel");

    public static void bootstrap(BootstrapContext<VillagerTrade> context) {
        register(context, FARMER_1_EMERALD_CAULIFLOWER, new VillagerTrade(
                new TradeCost(Items.EMERALD, 4),
                new ItemStackTemplate(ModItems.CAULIFLOWER),
                12, 10, 0.05f,
                Optional.empty(), List.of()));
        register(context, FARMER_1_DIAMOND_CAULIFLOWER_SEEDS, new VillagerTrade(
                new TradeCost(Items.DIAMOND, 12),
                new ItemStackTemplate(ModItems.CAULIFLOWER_SEEDS),
                12, 10, 0.05f,
                Optional.empty(), List.of()));

        register(context, FARMER_2_EMERALD_HONEY_BERRIES, new VillagerTrade(
                new TradeCost(Items.EMERALD, 16),
                new ItemStackTemplate(ModItems.HONEY_BERRIES),
                12, 10, 0.05f,
                Optional.empty(), List.of()));


        register(context, MASON_1_EMERALD_CHISEL, new VillagerTrade(
                new TradeCost(Items.EMERALD, 6),
                new ItemStackTemplate(ModItems.CHISEL),
                2, 19, 0.05f,
                Optional.empty(), List.of()));
    }


    private static ResourceKey<VillagerTrade> createKey(String name) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name));
    }

    private static void register(BootstrapContext<VillagerTrade> context, ResourceKey<VillagerTrade> resourceKey, VillagerTrade trade) {
        context.register(resourceKey, trade);
    }
}
