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

    public static void registerFluids() {
        MCCourse.LOGGER.info("Registering Fluids for " + MCCourse.MOD_ID);
    }
}
