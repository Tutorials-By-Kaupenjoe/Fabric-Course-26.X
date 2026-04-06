package net.kaupenjoe.mccourse.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kaupenjoe.mccourse.attachment.ModAttachmentTypes;
import net.kaupenjoe.mccourse.networking.packet.TestPayloadC2S;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;

// ANYTHING IN THERE WE ARE ON THE SERVER
public class ServerboundPackets {
    public static void handleTestPayload(TestPayloadC2S payload, ServerPlayNetworking.Context context) {
        // EntityType.COW.spawn(context.player().level(), context.player().getOnPos(), EntitySpawnReason.TRIGGERED);
        context.player().sendSystemMessage(Component.literal(payload.name()
                + " was sent by Kaupenjoe with value " + payload.value()
                + " and Kaupenjoe has Mana of " + context.player().getAttached(ModAttachmentTypes.MANA)), true);
    }
}
