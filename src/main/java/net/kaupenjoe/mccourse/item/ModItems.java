package net.kaupenjoe.mccourse.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.datagen.ModJukeboxSongs;
import net.kaupenjoe.mccourse.entity.ModEntities;
import net.kaupenjoe.mccourse.fluid.ModFluids;
import net.kaupenjoe.mccourse.food.ModFoodProperties;
import net.kaupenjoe.mccourse.item.custom.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModItems {
    public static final Item BISMUTH = registerItem("bismuth", Item::new);
    public static final Item RAW_BISMUTH = registerItem("raw_bismuth", Item::new);

    public static final Item CHISEL = registerItem("chisel", properties -> new ChiselItem(properties.durability(32)));
    public static final Item CAULIFLOWER = registerItem("cauliflower", properties -> new Item(properties.food(ModFoodProperties.CAULIFLOWER,
            ModFoodProperties.CAULIFLOWER_EFFECT)) {
        @Override
        public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
            builder.accept(Component.translatable("tooltip.mccourse.cauliflower"));
            super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        }
    });

    public static final Item STARLIGHT_ASHES = registerItem("starlight_ashes", Item::new);

    public static final Item BISMUTH_SWORD = registerItem("bismuth_sword",
            properties -> new Item(properties.sword(ModToolMaterials.BISMUTH, 3, -2.4f)));
    public static final Item BISMUTH_PICKAXE = registerItem("bismuth_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolMaterials.BISMUTH, 1, -2.8f)));
    public static final Item BISMUTH_SHOVEL = registerItem("bismuth_shovel",
            properties -> new ShovelItem(ModToolMaterials.BISMUTH, 1.5f, -3.0f, properties));
    public static final Item BISMUTH_AXE = registerItem("bismuth_axe",
            properties -> new AxeItem(ModToolMaterials.BISMUTH, 6, -3.2f, properties));
    public static final Item BISMUTH_HOE = registerItem("bismuth_hoe",
            properties -> new HoeItem(ModToolMaterials.BISMUTH, 0, -3f, properties));

    public static final Item BISMUTH_PAXEL = registerItem("bismuth_paxel",
            properties -> new PaxelItem(ModToolMaterials.BISMUTH, 1, -2.2f, properties));
    public static final Item BISMUTH_HAMMER = registerItem("bismuth_hammer",
            properties -> new HammerItem(properties.pickaxe(ModToolMaterials.BISMUTH, 8, -3.4f)));

    public static final Item BISMUTH_HELMET = registerItem("bismuth_helmet",
            properties -> new ModArmorItem(properties.humanoidArmor(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item BISMUTH_CHESTPLATE = registerItem("bismuth_chestplate",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item BISMUTH_LEGGINGS = registerItem("bismuth_leggings",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item BISMUTH_BOOTS = registerItem("bismuth_boots",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item BISMUTH_HORSE_ARMOR = registerItem("bismuth_horse_armor",
            properties -> new Item(properties.horseArmor(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL)));

    public static final Item KAUPEN_BOW = registerItem("kaupen_bow",
            properties -> new BowItem(properties.durability(500)));

    public static final Item CAULIFLOWER_SEEDS = registerItem("cauliflower_seeds",
            properties -> new BlockItem(ModBlocks.CAULIFLOWER_CROP, properties.useItemDescriptionPrefix()));
    public static final Item HONEY_BERRIES = registerItem("honey_berries",
            properties -> new BlockItem(ModBlocks.HONEY_BERRY_BUSH, properties
                    .food(ModFoodProperties.HONEY_BERRIES, ModFoodProperties.HONEY_BERRY_EFFECT).useItemDescriptionPrefix()));

    public static final Item RICE_SHOOT = registerItem("rice_shoot",
            properties -> new WaterCropBlockItem(ModBlocks.RICE_CROP, properties.useItemDescriptionPrefix()));
    public static final Item BAR_BRAWL_MUSIC_DISC = registerItem("bar_brawl_music_disc",
            properties -> new Item(properties.jukeboxPlayable(ModJukeboxSongs.BAR_BRAWL_KEY).stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final Item SPECTRE_STAFF = registerItem("spectre_staff",
            properties -> new Item(properties.stacksTo(1).rarity(Rarity.EPIC)));

    public static final Item BISMUTH_WATER_BUCKET = registerItem("bismuth_water_bucket",
            properties -> new BucketItem(ModFluids.BISMUTH_WATER_SOURCE, properties.stacksTo(1).craftRemainder(Items.BUCKET)));

    public static final Item GIRAFFE_SPAWN_EGG = registerItem("giraffe_spawn_egg",
            properties -> new SpawnEggItem(properties.spawnEgg(ModEntities.GIRAFFE)));
    public static final Item WARTURTLE_SPAWN_EGG = registerItem("warturtle_spawn_egg",
            properties -> new SpawnEggItem(properties.spawnEgg(ModEntities.WARTURTLE)));
    public static final Item DODO_SPAWN_EGG = registerItem("dodo_spawn_egg",
            properties -> new SpawnEggItem(properties.spawnEgg(ModEntities.DODO)));

    public static final Item IRON_WARTURTLE_ARMOR = registerItem("iron_warturtle_armor",
            properties -> new WarturtleArmorItem(properties.durability(200)));
    public static final Item GOLD_WARTURTLE_ARMOR = registerItem("gold_warturtle_armor",
            properties -> new WarturtleArmorItem(properties.durability(400)));
    public static final Item DIAMOND_WARTURTLE_ARMOR = registerItem("diamond_warturtle_armor",
            properties -> new WarturtleArmorItem(properties.durability(600)));
    public static final Item NETHERITE_WARTURTLE_ARMOR = registerItem("netherite_warturtle_armor",
            properties -> new WarturtleArmorItem(properties.durability(800)));
    public static final Item BISMUTH_WARTURTLE_ARMOR = registerItem("bismuth_warturtle_armor",
            properties -> new WarturtleArmorItem(properties.durability(1000)));

    public static final Item BLOODWOOD_BOAT = registerItem("bloodwood_boat",
            properties -> new BoatItem(ModEntities.BLOODWOOD_BOAT, properties.stacksTo(1)));
    public static final Item BLOODWOOD_CHEST_BOAT = registerItem("bloodwood_chest_boat",
            properties -> new BoatItem(ModEntities.BLOODWOOD_CHEST_BOAT, properties.stacksTo(1)));



    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name)))));
    }

    public static void registerModItems() {
        MCCourse.LOGGER.info("Registering Mod Items for " + MCCourse.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(BISMUTH);
            output.accept(RAW_BISMUTH);
        });
    }
}
