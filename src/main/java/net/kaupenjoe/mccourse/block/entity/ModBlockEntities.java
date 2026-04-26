package net.kaupenjoe.mccourse.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.block.entity.custom.CrystallizerBlockEntity;
import net.kaupenjoe.mccourse.block.entity.custom.MainPedestalBlockEntity;
import net.kaupenjoe.mccourse.block.entity.custom.PedestalBlockEntity;
import net.kaupenjoe.mccourse.block.entity.custom.SidePedestalBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import team.reborn.energy.api.EnergyStorage;

public class ModBlockEntities {
    public static final BlockEntityType<MainPedestalBlockEntity> MAIN_PEDESTAL_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "main_pedestal_be"),
                    FabricBlockEntityTypeBuilder.create(MainPedestalBlockEntity::new, ModBlocks.MAIN_PEDESTAL).build());
    public static final BlockEntityType<SidePedestalBlockEntity> SIDE_PEDESTAL_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "side_pedestal_be"),
                    FabricBlockEntityTypeBuilder.create(SidePedestalBlockEntity::new, ModBlocks.SIDE_PEDESTAL).build());

    public static final BlockEntityType<CrystallizerBlockEntity> CRYSTALLIZER_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "crystallizer_be"),
                    FabricBlockEntityTypeBuilder.create(CrystallizerBlockEntity::new, ModBlocks.CRYSTALLIZER).build());


    public static void registerBlockEntities() {
        MCCourse.LOGGER.info("Registering Block Entities for " + MCCourse.MOD_ID);

        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.getEnergyStorage(), CRYSTALLIZER_BE);
    }
}
