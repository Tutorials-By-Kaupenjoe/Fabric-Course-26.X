package net.kaupenjoe.mccourse.event;

import net.kaupenjoe.mccourse.attachment.ModAttachmentTypes;
import net.minecraft.server.level.ServerPlayer;

public class ModServerEvents {
    public static void onPlayerJoin(ServerPlayer player) {
        if(player.hasAttached(ModAttachmentTypes.MANA)) {
            player.setAttached(ModAttachmentTypes.MANA, player.getAttached(ModAttachmentTypes.MANA));
        } else {
            player.setAttached(ModAttachmentTypes.MANA, 5);
        }
    }

    public static void onPlayerCopyFrom(ServerPlayer oldPlayer, ServerPlayer newPlayer, boolean alive) {
        newPlayer.setAttached(ModAttachmentTypes.MANA, oldPlayer.getAttached(ModAttachmentTypes.MANA));
    }

    public static void onPlayerAfterRespawn(ServerPlayer oldPlayer, ServerPlayer newPlayer, boolean alive) {
        newPlayer.setAttached(ModAttachmentTypes.MANA, oldPlayer.getAttached(ModAttachmentTypes.MANA));
    }
}
