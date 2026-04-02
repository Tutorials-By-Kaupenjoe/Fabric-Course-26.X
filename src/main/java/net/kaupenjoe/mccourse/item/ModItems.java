package net.kaupenjoe.mccourse.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.food.ModFoodProperties;
import net.kaupenjoe.mccourse.item.custom.ChiselItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;

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
