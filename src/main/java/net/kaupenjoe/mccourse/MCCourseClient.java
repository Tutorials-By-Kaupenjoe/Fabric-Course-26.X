package net.kaupenjoe.mccourse;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.block.entity.ModBlockEntities;
import net.kaupenjoe.mccourse.block.entity.renderer.MainPedestalBlockEntityRenderer;
import net.kaupenjoe.mccourse.block.entity.renderer.PedestalBlockEntityRenderer;
import net.kaupenjoe.mccourse.block.entity.renderer.SidePedestalBlockEntityRenderer;
import net.kaupenjoe.mccourse.entity.ModEntities;
import net.kaupenjoe.mccourse.entity.client.*;
import net.kaupenjoe.mccourse.event.ModClientEvents;
import net.kaupenjoe.mccourse.fluid.ModFluids;
import net.kaupenjoe.mccourse.keybind.ModKeyMappings;
import net.kaupenjoe.mccourse.menu.ModMenuTypes;
import net.kaupenjoe.mccourse.menu.custom.*;
import net.kaupenjoe.mccourse.particle.BismuthParticle;
import net.kaupenjoe.mccourse.particle.ModParticles;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;

import java.util.List;

public class MCCourseClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeyMappings.registerKeys();

        ClientTickEvents.END_CLIENT_TICK.register(ModClientEvents::onEndTick);
        HudElementRegistry.addFirst(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "mana_display"), ModClientEvents::addFirstHudElement);

        BlockColorRegistry.register(List.of(BlockTintSources.foliage()), ModBlocks.COLORED_LEAVES);

        ParticleProviderRegistry.getInstance().register(ModParticles.BISMUTH_PARTICLE, BismuthParticle.Provider::new);

        FluidRenderingRegistry.register(ModFluids.BISMUTH_WATER_SOURCE, ModFluids.BISMUTH_WATER_FLOWING, new FluidModel.Unbaked(
                new Material(Identifier.withDefaultNamespace("block/water_still")),
                new Material(Identifier.withDefaultNamespace("block/water_flow")),
                new Material(Identifier.withDefaultNamespace("block/water_overlay")), _ -> 0xA1123125));

        ModelLayerRegistry.registerModelLayer(ModModelLayerLocations.GIRAFFE, GiraffeModel::createBodyLayer);
        EntityRenderers.register(ModEntities.GIRAFFE, GiraffeRenderer::new);

        EntityRenderers.register(ModEntities.CHAIR, ChairRenderer::new);

        ModelLayerRegistry.registerModelLayer(ModModelLayerLocations.WARTURTLE, WarturtleModel::createBodyLayer);
        EntityRenderers.register(ModEntities.WARTURTLE, WarturtleRenderer::new);

        MenuScreens.register(ModMenuTypes.WARTURTLE_MENU, WarturtleScreen::new);

        ModelLayerRegistry.registerModelLayer(ModModelLayerLocations.WARTURTLE_ARMOR, WarturtleModel::createBodyLayer);

        ModelLayerRegistry.registerModelLayer(ModModelLayerLocations.DODO, DodoModel::createBodyLayer);
        EntityRenderers.register(ModEntities.DODO, DodoRenderer::new);

        ModelLayerRegistry.registerModelLayer(ModModelLayerLocations.BLOODWOOD_BOAT, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(ModModelLayerLocations.BLOODWOOD_CHEST_BOAT, BoatModel::createChestBoatModel);

        EntityRenderers.register(ModEntities.BLOODWOOD_BOAT, context -> new BoatRenderer(context, ModModelLayerLocations.BLOODWOOD_BOAT));
        EntityRenderers.register(ModEntities.BLOODWOOD_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayerLocations.BLOODWOOD_CHEST_BOAT));

        ModelLayerRegistry.registerModelLayer(ModModelLayerLocations.TOMAHAWK, TomahawkModel::createBodyLayer);
        EntityRenderers.register(ModEntities.TOMAHAWK, TomahawkRenderer::new);

        BlockEntityRenderers.register(ModBlockEntities.MAIN_PEDESTAL_BE, MainPedestalBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.SIDE_PEDESTAL_BE, SidePedestalBlockEntityRenderer::new);
        MenuScreens.register(ModMenuTypes.PEDESTAL_MENU, PedestalScreen::new);

        MenuScreens.register(ModMenuTypes.CRYSTALLIZER_MENU, CrystallizerScreen::new);
        MenuScreens.register(ModMenuTypes.COAL_GENERATOR_MENU, CoalGeneratorScreen::new);
        MenuScreens.register(ModMenuTypes.BATTERY_MENU, BatteryScreen::new);
        MenuScreens.register(ModMenuTypes.GROWTH_CHAMBER_MENU, GrowthChamberScreen::new);
    }
}
