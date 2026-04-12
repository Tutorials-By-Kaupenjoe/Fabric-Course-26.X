package net.kaupenjoe.mccourse.attachment.handler;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kaupenjoe.mccourse.attachment.ModAttachmentTypes;
import net.kaupenjoe.mccourse.networking.packet.ManaPayloadS2C;
import net.kaupenjoe.mccourse.stat.ModStats;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class ManaHandler {
    public static void setMana(ServerPlayer player, int value) {
        player.setAttached(ModAttachmentTypes.MANA, value);
        // ServerPlayNetworking.send(player, new ManaPayloadS2C(0, value));
    }

    public static void addMana(ServerPlayer player, int value) {
        int newManaValue = player.getAttached(ModAttachmentTypes.MANA) + value;
        if(newManaValue > 5) {
            newManaValue = 5;
        }

        setMana(player, newManaValue);
    }

    public static void removeMana(ServerPlayer player, int value) {
        int newManaValue = player.getAttached(ModAttachmentTypes.MANA) - value;
        if(newManaValue < 0) {
            newManaValue = 0;
        }

        player.awardStat(ModStats.MANA_USED_TOTAL_STAT, 1);
        setMana(player, newManaValue);
    }

    public static boolean hasPlayerManaLeft(Player player) {
        return hasPlayerManaLeft(player, 1);
    }

    public static boolean hasPlayerManaLeft(Player player, int value) {
        return player.hasAttached(ModAttachmentTypes.MANA) && player.getAttached(ModAttachmentTypes.MANA) >= value;
    }

    public static int getMana(Player player) {
        return player.getAttached(ModAttachmentTypes.MANA);
    }
}
