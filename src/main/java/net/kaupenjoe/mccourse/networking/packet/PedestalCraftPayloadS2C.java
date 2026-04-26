package net.kaupenjoe.mccourse.networking.packet;

import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public record PedestalCraftPayloadS2C(BlockPos blockEntityPosition, ItemStack itemStack) implements CustomPacketPayload {
    public static final Type<PedestalCraftPayloadS2C> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "side_pedestal_sync"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PedestalCraftPayloadS2C> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            PedestalCraftPayloadS2C::blockEntityPosition,

            ItemStack.OPTIONAL_STREAM_CODEC,
            PedestalCraftPayloadS2C::itemStack,

            PedestalCraftPayloadS2C::new);


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
