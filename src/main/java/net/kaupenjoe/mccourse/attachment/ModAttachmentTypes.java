package net.kaupenjoe.mccourse.attachment;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;

public class ModAttachmentTypes {
    public static final AttachmentType<Integer> MANA = AttachmentRegistry.create(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "mana"),
            builder -> builder.persistent(Codec.INT).syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.all()));

    public static void registerModAttachments() {
        MCCourse.LOGGER.info("Registering Mod Attachments for " + MCCourse.MOD_ID);
    }
}
