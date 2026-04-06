package net.kaupenjoe.mccourse;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.kaupenjoe.mccourse.attachment.ModAttachmentTypes;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.consumeeffect.ModConsumeEffects;
import net.kaupenjoe.mccourse.data.ModDataComponents;
import net.kaupenjoe.mccourse.event.ModServerEvents;
import net.kaupenjoe.mccourse.item.ModCreativeModeTabs;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.keybind.ModKeyMappings;
import net.kaupenjoe.mccourse.networking.ModPackets;
import net.kaupenjoe.mccourse.registries.ModFuels;
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

		ModFuels.registerModFuels();
		ModConsumeEffects.registerModConsumeEffects();


		ServerPlayerEvents.JOIN.register(ModServerEvents::onPlayerJoin);
		ServerPlayerEvents.COPY_FROM.register(ModServerEvents::onPlayerCopyFrom);
		ServerPlayerEvents.AFTER_RESPAWN.register(ModServerEvents::onPlayerAfterRespawn);
	}
}