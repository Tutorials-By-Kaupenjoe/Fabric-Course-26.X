package net.kaupenjoe.mccourse.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.fabricmc.fabric.api.client.recipe.v1.sync.ClientRecipeSynchronizedEvent;
import net.fabricmc.fabric.api.recipe.v1.sync.SynchronizedRecipes;
import net.fabricmc.fabric.impl.recipe.sync.SynchronizedRecipesImpl;
import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.menu.custom.AtomicSeparatorScreen;
import net.kaupenjoe.mccourse.menu.custom.CrystallizerScreen;
import net.kaupenjoe.mccourse.menu.custom.GrowthChamberScreen;
import net.kaupenjoe.mccourse.menu.custom.PedestalScreen;
import net.kaupenjoe.mccourse.recipe.ModRecipes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

@JeiPlugin
public class JEIMCCourseModPlugin implements IModPlugin {
    @Override
    public Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "jei_plugin");
    }

    public static SynchronizedRecipes recipeMap = SynchronizedRecipesImpl.EMPTY;
    public JEIMCCourseModPlugin() {
        ClientRecipeSynchronizedEvent.EVENT.register((client, recipes) -> recipeMap = recipes);
    }

    // From Occultism
    // Under MIT License
    @SuppressWarnings({"unchecked", "rawtypes"})
    private <I extends RecipeInput, T extends Recipe<I>> List<RecipeHolder<T>> getRecipes(SynchronizedRecipes recipeMap, RecipeType<T> type) {
        return (List) recipeMap.getAllOfType(type);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CrystallizerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new PedestalRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new GrowthChamberRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new AtomicSeparatorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(ModJeiRecipeTypes.CRYSTALLIZER, this.getRecipes(recipeMap,
                ModRecipes.CRYSTALLIZER_TYPE));
        registration.addRecipes(ModJeiRecipeTypes.PEDESTAL, this.getRecipes(recipeMap,
                ModRecipes.PEDESTAL_TYPE));
        registration.addRecipes(ModJeiRecipeTypes.GROWTH_CHAMBER, this.getRecipes(recipeMap,
                ModRecipes.GROWTH_CHAMBER_TYPE));
        registration.addRecipes(ModJeiRecipeTypes.ATOMIC_SEPARATOR, this.getRecipes(recipeMap,
                ModRecipes.ATOMIC_SEPARATOR_TYPE));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CrystallizerScreen.class, 74, 30, 22, 20,
                ModJeiRecipeTypes.CRYSTALLIZER);
        registration.addRecipeClickArea(PedestalScreen.class, 74, 30, 22, 20,
                ModJeiRecipeTypes.PEDESTAL);
        registration.addRecipeClickArea(GrowthChamberScreen.class, 74, 30, 22, 20,
                ModJeiRecipeTypes.GROWTH_CHAMBER);
        registration.addRecipeClickArea(AtomicSeparatorScreen.class, 74, 30, 22, 20,
                ModJeiRecipeTypes.ATOMIC_SEPARATOR);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(ModJeiRecipeTypes.CRYSTALLIZER, new ItemStack(ModBlocks.CRYSTALLIZER.asItem()));
        registration.addCraftingStation(ModJeiRecipeTypes.PEDESTAL, new ItemStack(ModBlocks.MAIN_PEDESTAL.asItem()));
        registration.addCraftingStation(ModJeiRecipeTypes.GROWTH_CHAMBER, new ItemStack(ModBlocks.GROWTH_CHAMBER.asItem()));
        registration.addCraftingStation(ModJeiRecipeTypes.ATOMIC_SEPARATOR, new ItemStack(ModBlocks.ATOMIC_SEPARATOR.asItem()));
    }
}
