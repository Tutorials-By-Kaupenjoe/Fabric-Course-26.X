package net.kaupenjoe.mccourse.event;

import net.kaupenjoe.mccourse.attachment.ModAttachmentTypes;
import net.kaupenjoe.mccourse.attachment.handler.ManaHandler;
import net.minecraft.server.level.ServerPlayer;

public class ModServerEvents {
    public static void onPlayerJoin(ServerPlayer player) {
        if(player.hasAttached(ModAttachmentTypes.MANA)) {
            ManaHandler.setMana(player, player.getAttached(ModAttachmentTypes.MANA));
        } else {
            ManaHandler.setMana(player, 5);
        }
    }

    public static void onPlayerCopyFrom(ServerPlayer oldPlayer, ServerPlayer newPlayer, boolean alive) {
        ManaHandler.setMana(newPlayer, oldPlayer.getAttached(ModAttachmentTypes.MANA));
    }

    public static void onPlayerAfterRespawn(ServerPlayer oldPlayer, ServerPlayer newPlayer, boolean alive) {
        ManaHandler.setMana(newPlayer, oldPlayer.getAttached(ModAttachmentTypes.MANA));
    }
}
