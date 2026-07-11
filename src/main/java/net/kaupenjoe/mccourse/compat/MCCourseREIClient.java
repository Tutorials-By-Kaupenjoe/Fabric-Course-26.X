package net.kaupenjoe.mccourse.compat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.menu.custom.AtomicSeparatorScreen;
import net.kaupenjoe.mccourse.menu.custom.CrystallizerScreen;
import net.kaupenjoe.mccourse.menu.custom.GrowthChamberScreen;

public class MCCourseREIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CrystallizerCategory());
        registry.add(new PedestalRecipeCategory());
        registry.add(new GrowthChamberCategory());
        registry.add(new AtomicSeparatorCategory());

        registry.addWorkstations(MCCourseREICommon.CRYSTALLIZER, EntryStacks.of(ModBlocks.CRYSTALLIZER));
        registry.addWorkstations(MCCourseREICommon.PEDESTAL_CRAFTING, EntryStacks.of(ModBlocks.MAIN_PEDESTAL));
        registry.addWorkstations(MCCourseREICommon.PEDESTAL_CRAFTING, EntryStacks.of(ModBlocks.SIDE_PEDESTAL));

        registry.addWorkstations(MCCourseREICommon.GROWTH_CHAMBER, EntryStacks.of(ModBlocks.GROWTH_CHAMBER));
        registry.addWorkstations(MCCourseREICommon.ATOMIC_SEPARATOR, EntryStacks.of(ModBlocks.ATOMIC_SEPARATOR));
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
                        ((screen.height - 166) / 2) + 30, 20, 25),
                CrystallizerScreen.class, MCCourseREICommon.CRYSTALLIZER);

        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 45,
                        ((screen.height - 166) / 2) + 10, 80, 60),
                GrowthChamberScreen.class, MCCourseREICommon.GROWTH_CHAMBER);

        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 45,
                        ((screen.height - 166) / 2) + 30, 80, 20),
                AtomicSeparatorScreen.class, MCCourseREICommon.ATOMIC_SEPARATOR);
    }
}
