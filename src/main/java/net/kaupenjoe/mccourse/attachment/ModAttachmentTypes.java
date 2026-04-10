package net.kaupenjoe.mccourse.attachment;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

public class ModAttachmentTypes {
    public static final AttachmentType<Integer> MANA = AttachmentRegistry.createPersistent(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "mana"),
            Codec.INT);
    public static final AttachmentType<BlockPos> HOME_POS = AttachmentRegistry.create(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "home_pos"),
            blockPosBuilder -> blockPosBuilder.persistent(BlockPos.CODEC).syncWith(BlockPos.STREAM_CODEC, AttachmentSyncPredicate.all()));

    public static void registerModAttachments() {
        MCCourse.LOGGER.info("Registering Mod Attachments for " + MCCourse.MOD_ID);
    }
}
