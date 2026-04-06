package net.kaupenjoe.mccourse.networking.packet;

import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ManaPayloadS2C(int oldValue, int newValue) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ManaPayloadS2C> TYPE = new Type<>(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "mana_payload"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ManaPayloadS2C> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            ManaPayloadS2C::oldValue,

            ByteBufCodecs.VAR_INT,
            ManaPayloadS2C::newValue,

            ManaPayloadS2C::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
