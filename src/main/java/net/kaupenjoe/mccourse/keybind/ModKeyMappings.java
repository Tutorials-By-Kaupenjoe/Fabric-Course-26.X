package net.kaupenjoe.mccourse.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.client.KeyMapping;

public class ModKeyMappings {
    public static final KeyMapping K_KEYBIND = KeyMappingHelper.registerKeyMapping(
            new KeyMapping("key.mccourse.kaupen_key", InputConstants.KEY_K, KeyMapping.Category.MISC));


    public static void registerKeys() {
        MCCourse.LOGGER.info("Registering Keys for " + MCCourse.MOD_ID);
    }
}
