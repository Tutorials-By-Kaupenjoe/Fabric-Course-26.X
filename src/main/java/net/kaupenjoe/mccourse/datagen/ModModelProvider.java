package net.kaupenjoe.mccourse.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.item.ModArmorMaterials;
import net.kaupenjoe.mccourse.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        var bismuthFamily = blockModelGenerators.family(ModBlocks.BISMUTH_BLOCK);

        blockModelGenerators.createTrivialCube(ModBlocks.RAW_BISMUTH_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.BISMUTH_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.BISMUTH_DEEPSLATE_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.BISMUTH_NETHER_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.BISMUTH_END_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.MAGIC_BLOCK);

        bismuthFamily.stairs(ModBlocks.BISMUTH_STAIRS);
        bismuthFamily.slab(ModBlocks.BISMUTH_SLAB);

        bismuthFamily.button(ModBlocks.BISMUTH_BUTTON);
        bismuthFamily.pressurePlate(ModBlocks.BISMUTH_PRESSURE_PLATE);

        bismuthFamily.fence(ModBlocks.BISMUTH_FENCE);
        bismuthFamily.fenceGate(ModBlocks.BISMUTH_FENCE_GATE);
        bismuthFamily.wall(ModBlocks.BISMUTH_WALL);

        blockModelGenerators.createDoor(ModBlocks.BISMUTH_DOOR);
        blockModelGenerators.createTrapdoor(ModBlocks.BISMUTH_TRAPDOOR);

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.BISMUTH, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.RAW_BISMUTH, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CHISEL, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CAULIFLOWER, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.STARLIGHT_ASHES, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_PAXEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerators.generateTrimmableItem(ModItems.BISMUTH_HELMET, ModArmorMaterials.BISMUTH_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerators.generateTrimmableItem(ModItems.BISMUTH_CHESTPLATE, ModArmorMaterials.BISMUTH_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerators.generateTrimmableItem(ModItems.BISMUTH_LEGGINGS, ModArmorMaterials.BISMUTH_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerators.generateTrimmableItem(ModItems.BISMUTH_BOOTS, ModArmorMaterials.BISMUTH_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);




    }
}
