package net.kaupenjoe.mccourse.villager;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PoiHelper;
import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.datagen.villager.ModTradeSets;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.level.block.Block;

public class ModVillagers {
    public static final ResourceKey<PoiType> KAUPEN_POI_KEY = registerPoiKey("kaupen_poi");
    public static final PoiType KAUPEN_POI = registerPOI("kaupen_poi", ModBlocks.MAGIC_BLOCK);

    public static final ResourceKey<VillagerProfession> KAUPENGER_KEY =
            ResourceKey.create(Registries.VILLAGER_PROFESSION, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "kaupenger"));
    public static final VillagerProfession KAUPENGER = registerVillagerProfession("kaupenger", new VillagerProfession(
            Component.literal("Kaupenger"), holder -> holder.is(KAUPEN_POI_KEY), holder -> holder.is(KAUPEN_POI_KEY),
            ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_LIBRARIAN, Int2ObjectMap.ofEntries(
                    Int2ObjectMap.entry(1, ModTradeSets.KAUPENGER_LEVEL_1),
                    Int2ObjectMap.entry(2, ModTradeSets.KAUPENGER_LEVEL_2))));


    private static VillagerProfession registerVillagerProfession(String name, VillagerProfession profession) {
        return Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name), profession);
    }

    private static PoiType registerPOI(String name, Block block) {
        return PoiHelper.register(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name),
                1, 1, block);
    }

    private static ResourceKey<PoiType> registerPoiKey(String name) {
        return ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name));
    }

    public static void registerVillagers() {
        MCCourse.LOGGER.info("Registering Villagers and POIs for " + MCCourse.MOD_ID);
    }
}
