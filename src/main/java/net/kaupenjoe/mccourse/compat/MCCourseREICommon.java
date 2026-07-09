package net.kaupenjoe.mccourse.compat;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REICommonPlugin;
import me.shedaniel.rei.api.common.registry.display.ServerDisplayRegistry;
import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.recipe.custom.CrystallizerRecipe;
import net.kaupenjoe.mccourse.recipe.custom.PedestalRecipe;
import net.minecraft.resources.Identifier;

public class MCCourseREICommon implements REICommonPlugin {
    public static final CategoryIdentifier<CrystallizerDisplay> CRYSTALLIZER = CategoryIdentifier.of(MCCourse.MOD_ID, "crystallizer");
    public static final CategoryIdentifier<PedestalCraftingDisplay> PEDESTAL_CRAFTING = CategoryIdentifier.of(MCCourse.MOD_ID, "pedestal_crafting");

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        REICommonPlugin.super.registerDisplaySerializer(registry);

        registry.register(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "crystallizer_serializer"), CrystallizerDisplay.SERIALIZER);
        registry.register(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "pedestal_crafting_serializer"), PedestalCraftingDisplay.SERIALIZER);
    }

    @Override
    public void registerDisplays(ServerDisplayRegistry registry) {
        REICommonPlugin.super.registerDisplays(registry);

        registry.beginRecipeFiller(CrystallizerRecipe.class).fill(CrystallizerDisplay::new);
        registry.beginRecipeFiller(PedestalRecipe.class).fill(PedestalCraftingDisplay::new);
    }
}
