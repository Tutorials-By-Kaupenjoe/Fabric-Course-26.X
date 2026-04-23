package net.kaupenjoe.mccourse.menu;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.menu.custom.CrystallizerMenu;
import net.kaupenjoe.mccourse.menu.custom.PedestalMenu;
import net.kaupenjoe.mccourse.menu.custom.WarturtleMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final MenuType<WarturtleMenu> WARTURTLE_MENU =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "warturtle_menu"),
                    new ExtendedMenuType<>(WarturtleMenu::create, UUIDUtil.STREAM_CODEC));

    public static final MenuType<PedestalMenu> PEDESTAL_MENU =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "pedestal_menu"),
                    new ExtendedMenuType<>(PedestalMenu::new, BlockPos.STREAM_CODEC));

    public static final MenuType<CrystallizerMenu> CRYSTALLIZER_MENU =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "crystallizer_menu"),
                    new ExtendedMenuType<>(CrystallizerMenu::new, BlockPos.STREAM_CODEC));


    public static void registerModMenuTypes() {
        MCCourse.LOGGER.info("Registering Mod Menu Types for " + MCCourse.MOD_ID);
    }
}
