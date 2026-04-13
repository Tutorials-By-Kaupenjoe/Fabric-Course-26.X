package net.kaupenjoe.mccourse.fluid;

import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.material.FlowingFluid;

public class ModFluids {
    public static final FlowingFluid BISMUTH_WATER_SOURCE = Registry.register(BuiltInRegistries.FLUID,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "bismuth_water_source"), new BismuthWaterFluid.Source());
    public static final FlowingFluid BISMUTH_WATER_FLOWING = Registry.register(BuiltInRegistries.FLUID,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "bismuth_water_flowing"), new BismuthWaterFluid.Flowing());

    public static final FluidModel.Unbaked BISMUTH_WATER_MODEL = new FluidModel.Unbaked(
            new Material(Identifier.withDefaultNamespace("block/water_still")),
            new Material(Identifier.withDefaultNamespace("block/water_flow")),
            new Material(Identifier.withDefaultNamespace("block/water_overlay")), _ -> 0xA1123125);

    public static void registerFluids() {
        MCCourse.LOGGER.info("Registering Fluids for " + MCCourse.MOD_ID);
    }
}
