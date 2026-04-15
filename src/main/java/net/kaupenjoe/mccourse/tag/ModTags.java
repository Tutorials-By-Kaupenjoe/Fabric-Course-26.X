package net.kaupenjoe.mccourse.tag;

import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_BISMUTH_TOOL = createTag("needs_bismuth_tool");
        public static final TagKey<Block> INCORRECT_FOR_BISMUTH_TOOL = createTag("incorrect_for_bismuth_tool");

        public static final TagKey<Block> PAXEL_MINEABLE = createTag("mineable/paxel");
        public static final TagKey<Block> BLOODWOOD_LOGS = createTag("bloodwood_logs");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        public static final TagKey<Item> BISMUTH_REPAIRABLE = createTag("bismuth_repairables");

        public static final TagKey<Item> BLOODWOOD_LOGS = createTag("bloodwood_logs");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name));
        }
    }

    public static class Trades {
        public static final TagKey<VillagerTrade> KAUPENGER_LEVEL_1 = createTag("kaupenger/level_1");
        public static final TagKey<VillagerTrade> KAUPENGER_LEVEL_2 = createTag("kaupenger/level_2");


        private static TagKey<VillagerTrade> createTag(String name) {
            return TagKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name));
        }
    }
}
