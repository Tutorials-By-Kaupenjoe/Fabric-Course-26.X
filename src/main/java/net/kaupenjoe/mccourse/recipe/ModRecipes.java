package net.kaupenjoe.mccourse.recipe;

import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.recipe.custom.CrystallizerRecipe;
import net.kaupenjoe.mccourse.recipe.custom.PedestalRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {
    public static final RecipeSerializer<CrystallizerRecipe> CRYSTALLIZER_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "crystallizing"),
            new RecipeSerializer<>(CrystallizerRecipe.CODEC, CrystallizerRecipe.STREAM_CODEC));
    public static final RecipeType<CrystallizerRecipe> CRYSTALLIZER_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "crystallizing"),
            new RecipeType<CrystallizerRecipe>() {
                @Override
                public String toString() {
                    return "crystallizing";
                }
            });

    public static final RecipeSerializer<PedestalRecipe> PEDESTAL_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "pedestal_crafting"),
            new RecipeSerializer<>(PedestalRecipe.CODEC, PedestalRecipe.STREAM_CODEC));
    public static final RecipeType<PedestalRecipe> PEDESTAL_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "pedestal_crafting"),
            new RecipeType<PedestalRecipe>() {
                @Override
                public String toString() {
                    return "pedestal_crafting";
                }
            });


    public static void registerModRecipes() {
        MCCourse.LOGGER.info("Registering Custom Recipe Types for " + MCCourse.MOD_ID);
    }
}
