package net.kaupenjoe.mccourse.attachment;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.resources.Identifier;

public class ModAttachmentTypes {
    public static final AttachmentType<Integer> MANA = AttachmentRegistry.createPersistent(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "mana"),
            Codec.INT);

    public static void registerModAttachments() {
        MCCourse.LOGGER.info("Registering Mod Attachments for " + MCCourse.MOD_ID);
    }
}
