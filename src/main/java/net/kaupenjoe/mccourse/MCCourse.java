package net.kaupenjoe.mccourse;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.kaupenjoe.mccourse.attachment.ModAttachmentTypes;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.consumeeffect.ModConsumeEffects;
import net.kaupenjoe.mccourse.data.ModDataComponents;
import net.kaupenjoe.mccourse.effect.ModEffects;
import net.kaupenjoe.mccourse.event.ModAttackEntityHandler;
import net.kaupenjoe.mccourse.event.ModServerEvents;
import net.kaupenjoe.mccourse.item.ModCreativeModeTabs;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.keybind.ModKeyMappings;
import net.kaupenjoe.mccourse.loot.ModLootTableModifiers;
import net.kaupenjoe.mccourse.networking.ModPackets;
import net.kaupenjoe.mccourse.particle.ModParticles;
import net.kaupenjoe.mccourse.potion.ModPotions;
import net.kaupenjoe.mccourse.registries.ModCommands;
import net.kaupenjoe.mccourse.registries.ModCompostables;
import net.kaupenjoe.mccourse.registries.ModFuels;
import net.kaupenjoe.mccourse.registries.ModPotionsRecipes;
import net.kaupenjoe.mccourse.sound.ModSounds;
import net.kaupenjoe.mccourse.villager.ModVillagers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// An extremely important comment
public class MCCourse implements ModInitializer {
	public static final String MOD_ID = "mccourse";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerCreativeModeTabs();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModDataComponents.registerDataComponents();
		ModPackets.registerPackets();

		ModAttachmentTypes.registerModAttachments();
		ModConsumeEffects.registerModConsumeEffects();

		ModSounds.registerSounds();
		ModEffects.registerEffects();

		ModPotions.registerPotions();
		ModVillagers.registerVillagers();

		ModParticles.registerParticles();



		ModFuels.registerModFuels();
		ModCompostables.registerCompostables();
		ModCommands.registerCommands();
		ModPotionsRecipes.registerBrewingRecipes();


		ServerPlayerEvents.JOIN.register(ModServerEvents::onPlayerJoin);
		ServerPlayerEvents.COPY_FROM.register(ModServerEvents::onPlayerCopyFrom);
		ServerPlayerEvents.AFTER_RESPAWN.register(ModServerEvents::onPlayerAfterRespawn);

		LootTableEvents.MODIFY.register(ModLootTableModifiers::modifyLootTables);

		AttackEntityCallback.EVENT.register(new ModAttackEntityHandler());
	}
}