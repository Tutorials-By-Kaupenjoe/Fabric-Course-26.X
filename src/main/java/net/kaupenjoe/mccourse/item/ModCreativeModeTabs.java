package net.kaupenjoe.mccourse.item;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab BISMUTH_ITEMS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "bismuth_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BISMUTH))
                    .title(Component.translatable("creativetab.mccourse.bismuth_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.BISMUTH);
                        output.accept(ModItems.RAW_BISMUTH);


                    }).build());


    public static void registerCreativeModeTabs() {
        MCCourse.LOGGER.info("Registering Creative Mode Tabs for " + MCCourse.MOD_ID);
    }
}
