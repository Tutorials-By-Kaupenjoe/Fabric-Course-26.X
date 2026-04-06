package net.kaupenjoe.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.block.custom.CauliflowerCropBlock;
import net.kaupenjoe.mccourse.item.ModItems;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.BISMUTH_BLOCK);
        dropSelf(ModBlocks.RAW_BISMUTH_BLOCK);

        add(ModBlocks.BISMUTH_ORE, createOreDrop(ModBlocks.BISMUTH_ORE, ModItems.RAW_BISMUTH));
        add(ModBlocks.BISMUTH_DEEPSLATE_ORE, multipleOreDrops(ModBlocks.BISMUTH_DEEPSLATE_ORE, ModItems.RAW_BISMUTH, 3, 6));
        add(ModBlocks.BISMUTH_NETHER_ORE, multipleOreDrops(ModBlocks.BISMUTH_NETHER_ORE, ModItems.RAW_BISMUTH, 5, 8));
        add(ModBlocks.BISMUTH_END_ORE, multipleOreDrops(ModBlocks.BISMUTH_END_ORE, ModItems.RAW_BISMUTH, 7, 10));

        dropSelf(ModBlocks.MAGIC_BLOCK);

        dropSelf(ModBlocks.BISMUTH_STAIRS);
        add(ModBlocks.BISMUTH_SLAB, createSlabItemTable(ModBlocks.BISMUTH_SLAB));

        dropSelf(ModBlocks.BISMUTH_BUTTON);
        dropSelf(ModBlocks.BISMUTH_PRESSURE_PLATE);

        dropSelf(ModBlocks.BISMUTH_FENCE);
        dropSelf(ModBlocks.BISMUTH_FENCE_GATE);
        dropSelf(ModBlocks.BISMUTH_WALL);

        add(ModBlocks.BISMUTH_DOOR, createDoorTable(ModBlocks.BISMUTH_DOOR));
        dropSelf(ModBlocks.BISMUTH_TRAPDOOR);

        dropSelf(ModBlocks.BISMUTH_LAMP);

        add(ModBlocks.CAULIFLOWER_CROP, createCropDrops(ModBlocks.CAULIFLOWER_CROP, ModItems.CAULIFLOWER, ModItems.CAULIFLOWER_SEEDS,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CAULIFLOWER_CROP)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CauliflowerCropBlock.AGE, CauliflowerCropBlock.MAX_AGE))));

        dropSelf(ModBlocks.PETUNIA);
        add(ModBlocks.POTTED_PETUNIA, createPotFlowerItemTable(ModBlocks.PETUNIA));
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return this.createSilkTouchDispatchTable(
                drop, this.applyExplosionDecay(drop,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }
}
