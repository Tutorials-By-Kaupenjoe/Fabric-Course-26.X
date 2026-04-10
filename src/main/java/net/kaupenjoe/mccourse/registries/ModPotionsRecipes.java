package net.kaupenjoe.mccourse.registries;

import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.kaupenjoe.mccourse.potion.ModPotions;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

public class ModPotionsRecipes {
    public static void registerBrewingRecipes() {
        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(Items.DIRT), ModPotions.STINKY_POTION);
        });
    }
}
