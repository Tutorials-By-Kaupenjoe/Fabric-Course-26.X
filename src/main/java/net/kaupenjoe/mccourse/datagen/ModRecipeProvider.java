package net.kaupenjoe.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                List<ItemLike> BISMUTH_SMELTABLES = List.of(ModItems.RAW_BISMUTH, ModBlocks.BISMUTH_ORE,
                        ModBlocks.BISMUTH_DEEPSLATE_ORE, ModBlocks.BISMUTH_NETHER_ORE, ModBlocks.BISMUTH_END_ORE);

                oreSmelting(BISMUTH_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.BISMUTH,
                        0.25f, 200, "bismuth");
                oreBlasting(BISMUTH_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.BISMUTH,
                        0.25f, 100, "bismuth");

                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.BISMUTH,
                        RecipeCategory.DECORATIONS, ModBlocks.BISMUTH_BLOCK);

                shaped(RecipeCategory.MISC, ModBlocks.RAW_BISMUTH_BLOCK, 1)
                        .pattern("RRR")
                        .pattern("RRR")
                        .pattern("RRR")
                        .define('R', ModItems.RAW_BISMUTH)
                        .unlockedBy(getHasName(ModItems.RAW_BISMUTH), has(ModItems.RAW_BISMUTH))
                        .save(output);

                shapeless(RecipeCategory.MISC, ModItems.RAW_BISMUTH, 9)
                        .requires(ModBlocks.RAW_BISMUTH_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.RAW_BISMUTH_BLOCK), has(ModBlocks.RAW_BISMUTH_BLOCK))
                        .save(output);

                shapeless(RecipeCategory.MISC, ModItems.RAW_BISMUTH, 2)
                        .requires(ModBlocks.BISMUTH_NETHER_ORE)
                        .unlockedBy(getHasName(ModBlocks.BISMUTH_NETHER_ORE), has(ModBlocks.BISMUTH_NETHER_ORE))
                        .save(output, "raw_bismuth_from_nether_ore");

                stairBuilder(ModBlocks.BISMUTH_STAIRS, Ingredient.of(ModBlocks.BISMUTH_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.BISMUTH_BLOCK), has(ModBlocks.BISMUTH_BLOCK))
                        .save(output);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BISMUTH_SLAB, ModBlocks.BISMUTH_BLOCK);

                buttonBuilder(ModBlocks.BISMUTH_BUTTON, Ingredient.of(ModBlocks.BISMUTH_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.BISMUTH_BLOCK), has(ModBlocks.BISMUTH_BLOCK))
                        .save(output);
                pressurePlate(ModBlocks.BISMUTH_PRESSURE_PLATE, ModBlocks.BISMUTH_BLOCK);

                fenceBuilder(ModBlocks.BISMUTH_FENCE, Ingredient.of(ModBlocks.BISMUTH_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.BISMUTH_BLOCK), has(ModBlocks.BISMUTH_BLOCK))
                        .save(output);
                fenceGateBuilder(ModBlocks.BISMUTH_FENCE_GATE, Ingredient.of(ModBlocks.BISMUTH_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.BISMUTH_BLOCK), has(ModBlocks.BISMUTH_BLOCK))
                        .save(output);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BISMUTH_WALL, ModBlocks.BISMUTH_BLOCK);

                doorBuilder(ModBlocks.BISMUTH_DOOR, Ingredient.of(ModItems.BISMUTH))
                        .unlockedBy(getHasName(ModItems.BISMUTH), has(ModItems.BISMUTH))
                        .save(output);
                trapdoorBuilder(ModBlocks.BISMUTH_TRAPDOOR, Ingredient.of(ModItems.BISMUTH))
                        .unlockedBy(getHasName(ModItems.BISMUTH), has(ModItems.BISMUTH))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.BISMUTH_SWORD)
                        .pattern("B")
                        .pattern("B")
                        .pattern("S")
                        .define('B', ModItems.BISMUTH)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.BISMUTH), has(ModItems.BISMUTH))
                        .save(output);
                shaped(RecipeCategory.TOOLS, ModItems.BISMUTH_PICKAXE)
                        .pattern("BBB")
                        .pattern(" S ")
                        .pattern(" S ")
                        .define('B', ModItems.BISMUTH)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.BISMUTH), has(ModItems.BISMUTH))
                        .save(output);
                shaped(RecipeCategory.TOOLS, ModItems.BISMUTH_SHOVEL)
                        .pattern("B")
                        .pattern("S")
                        .pattern("S")
                        .define('B', ModItems.BISMUTH)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.BISMUTH), has(ModItems.BISMUTH))
                        .save(output);
                shaped(RecipeCategory.TOOLS, ModItems.BISMUTH_AXE)
                        .pattern("BB")
                        .pattern("BS")
                        .pattern(" S")
                        .define('B', ModItems.BISMUTH)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.BISMUTH), has(ModItems.BISMUTH))
                        .save(output);
                shaped(RecipeCategory.TOOLS, ModItems.BISMUTH_HOE)
                        .pattern("BB")
                        .pattern(" S")
                        .pattern(" S")
                        .define('B', ModItems.BISMUTH)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.BISMUTH), has(ModItems.BISMUTH))
                        .save(output);
                shaped(RecipeCategory.TOOLS, ModItems.BISMUTH_PAXEL)
                        .pattern("PAS")
                        .define('P', ModItems.BISMUTH_PICKAXE)
                        .define('A', ModItems.BISMUTH_AXE)
                        .define('S', ModItems.BISMUTH_SHOVEL)
                        .unlockedBy(getHasName(ModItems.BISMUTH_PICKAXE), has(ModItems.BISMUTH_PICKAXE))
                        .unlockedBy(getHasName(ModItems.BISMUTH_AXE), has(ModItems.BISMUTH_AXE))
                        .unlockedBy(getHasName(ModItems.BISMUTH_SHOVEL), has(ModItems.BISMUTH_SHOVEL))
                        .save(output);
                shaped(RecipeCategory.TOOLS, ModItems.BISMUTH_HAMMER)
                        .pattern("BBB")
                        .pattern("BSB")
                        .pattern(" S ")
                        .define('B', ModBlocks.BISMUTH_BLOCK)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModBlocks.BISMUTH_BLOCK), has(ModBlocks.BISMUTH_BLOCK))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.BISMUTH_HELMET)
                        .pattern("BBB")
                        .pattern("B B")
                        .define('B', ModItems.BISMUTH)
                        .unlockedBy(getHasName(ModItems.BISMUTH), has(ModItems.BISMUTH))
                        .save(output);
                shaped(RecipeCategory.COMBAT, ModItems.BISMUTH_CHESTPLATE)
                        .pattern("B B")
                        .pattern("BBB")
                        .pattern("BBB")
                        .define('B', ModItems.BISMUTH)
                        .unlockedBy(getHasName(ModItems.BISMUTH), has(ModItems.BISMUTH))
                        .save(output);
                shaped(RecipeCategory.COMBAT, ModItems.BISMUTH_LEGGINGS)
                        .pattern("BBB")
                        .pattern("B B")
                        .pattern("B B")
                        .define('B', ModItems.BISMUTH)
                        .unlockedBy(getHasName(ModItems.BISMUTH), has(ModItems.BISMUTH))
                        .save(output);
                shaped(RecipeCategory.COMBAT, ModItems.BISMUTH_BOOTS)
                        .pattern("B B")
                        .pattern("B B")
                        .define('B', ModItems.BISMUTH)
                        .unlockedBy(getHasName(ModItems.BISMUTH), has(ModItems.BISMUTH))
                        .save(output);

                shaped(RecipeCategory.DECORATIONS, ModBlocks.BISMUTH_LAMP)
                        .pattern("BBB")
                        .pattern("BRB")
                        .pattern("BBB")
                        .define('B', ModItems.BISMUTH)
                        .define('R', Items.REDSTONE)
                        .unlockedBy(getHasName(ModItems.BISMUTH), has(ModItems.BISMUTH))
                        .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.KAUPEN_BOW)
                        .pattern("S# ")
                        .pattern("S B")
                        .pattern("S# ")
                        .define('B', ModItems.BISMUTH)
                        .define('S', Items.STRING)
                        .define('#', Items.STICK)
                        .unlockedBy(getHasName(ModItems.BISMUTH), has(ModItems.BISMUTH))
                        .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);

            }
        };
    }

    @Override
    public String getName() {
        return "MCCourse Recipes";
    }
}
