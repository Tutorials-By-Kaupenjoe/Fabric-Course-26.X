package net.kaupenjoe.mccourse.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.kaupenjoe.mccourse.attachment.ModAttachmentTypes;
import net.kaupenjoe.mccourse.networking.packet.ManaPayloadS2C;

// Here we are on THE CLIENT!
public class ClientboundPackets {
    public static void handleManaPayload(ManaPayloadS2C manaPayload, ClientPlayNetworking.Context context) {
        context.player().setAttached(ModAttachmentTypes.MANA, manaPayload.newValue());
    }
}
