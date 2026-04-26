package net.kaupenjoe.mccourse.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.kaupenjoe.mccourse.attachment.ModAttachmentTypes;
import net.kaupenjoe.mccourse.block.entity.custom.SidePedestalBlockEntity;
import net.kaupenjoe.mccourse.networking.packet.ManaPayloadS2C;
import net.kaupenjoe.mccourse.networking.packet.PedestalCraftPayloadS2C;

// Here we are on THE CLIENT!
public class ClientboundPackets {
    public static void handleManaPayload(ManaPayloadS2C manaPayload, ClientPlayNetworking.Context context) {
        context.player().setAttached(ModAttachmentTypes.MANA, manaPayload.newValue());
    }

    public static void handlePedestalCrafting(PedestalCraftPayloadS2C pedestalCraftPayloadS2C, ClientPlayNetworking.Context context) {
        if(context.player().level().getBlockEntity(pedestalCraftPayloadS2C.blockEntityPosition()) instanceof SidePedestalBlockEntity sidePedestalBlockEntity) {
            sidePedestalBlockEntity.setTheItem(pedestalCraftPayloadS2C.itemStack());
        }
    }
}
