package net.kaupenjoe.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.datagen.recipe.CrystallizerRecipeBuilder;
import net.kaupenjoe.mccourse.datagen.recipe.GrowthChamberRecipeBuilder;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

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

                shaped(RecipeCategory.DECORATIONS, ModBlocks.CHAIR)
                        .pattern("PPP")
                        .pattern("S S")
                        .pattern("S S")
                        .define('P', Blocks.OAK_PLANKS)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .unlockedBy(getHasName(Blocks.OAK_PLANKS), has(Blocks.OAK_PLANKS))
                        .save(output);

                woodFromLogs(ModBlocks.BLOODWOOD_WOOD, ModBlocks.BLOODWOOD_LOG);
                woodFromLogs(ModBlocks.STRIPPED_BLOODWOOD_WOOD, ModBlocks.STRIPPED_BLOODWOOD_LOG);
                planksFromLogs(ModBlocks.BLOODWOOD_PLANKS, ModTags.Items.BLOODWOOD_LOGS, 4);

                CrystallizerRecipeBuilder.crystallizerRecipe(RecipeCategory.MISC, Ingredient.of(ModItems.CAULIFLOWER), ModItems.RICE_SHOOT, 2)
                        .unlockedBy(getHasName(ModItems.CAULIFLOWER), has(ModItems.CAULIFLOWER))
                        .save(output, "mccourse:rice_shoot_from_crystallizing");
                CrystallizerRecipeBuilder.crystallizerRecipe(RecipeCategory.MISC, Ingredient.of(Items.STICK), Items.END_ROD, 2)
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output, "mccourse:end_rod_from_crystallizing");
                CrystallizerRecipeBuilder.crystallizerRecipe(RecipeCategory.MISC, Ingredient.of(ModItems.RAW_BISMUTH), ModItems.BISMUTH, 3)
                        .unlockedBy(getHasName(ModItems.RAW_BISMUTH), has(ModItems.RAW_BISMUTH))
                        .save(output, "mccourse:bismuth_from_crystallizing");
                CrystallizerRecipeBuilder.crystallizerRecipe(RecipeCategory.MISC, Ingredient.of(Blocks.DIRT), Items.NETHER_STAR)
                        .unlockedBy(getHasName(Blocks.DIRT), has(Blocks.DIRT))
                        .save(output, "mccourse:nether_star_from_crystallizing");


                GrowthChamberRecipeBuilder.growthChamber(RecipeCategory.MISC, List.of(
                                        Ingredient.of(Items.SLIME_BALL),
                                        Ingredient.of(Items.SLIME_BALL),
                                        Ingredient.of(Items.SLIME_BALL)),
                                Items.SLIME_BLOCK)
                        .unlockedBy(getHasName(Items.SLIME_BALL), has(Items.SLIME_BALL))
                        .save(output, "mccourse:slime_block_from_growing");

                GrowthChamberRecipeBuilder.growthChamber(RecipeCategory.MISC, List.of(
                                        Ingredient.of(Items.REDSTONE),
                                        Ingredient.of(Items.LAPIS_LAZULI),
                                        Ingredient.of(Items.REDSTONE)),
                                Items.DIAMOND)
                        .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                        .unlockedBy(getHasName(Items.LAPIS_LAZULI), has(Items.LAPIS_LAZULI))
                        .save(output, "mccourse:diamond_from_growing");

            }
        };
    }

    @Override
    public String getName() {
        return "MCCourse Recipes";
    }
}
