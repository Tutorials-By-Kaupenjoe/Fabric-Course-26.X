package net.kaupenjoe.mccourse.tag;

import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name));
        }
    }
}
