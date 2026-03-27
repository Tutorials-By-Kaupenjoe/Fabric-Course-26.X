package net.kaupenjoe.mccourse;

import net.fabricmc.api.ModInitializer;

import net.kaupenjoe.mccourse.item.ModCreativeModeTabs;
import net.kaupenjoe.mccourse.item.ModItems;
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

	}
}