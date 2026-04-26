package net.kaupenjoe.mccourse.block.entity.custom;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kaupenjoe.mccourse.block.entity.ModBlockEntities;
import net.kaupenjoe.mccourse.menu.custom.PedestalMenu;
import net.kaupenjoe.mccourse.networking.packet.PedestalCraftPayloadS2C;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jspecify.annotations.Nullable;

public class SidePedestalBlockEntity extends PedestalBlockEntity {
    public SidePedestalBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.SIDE_PEDESTAL_BE, worldPosition, blockState);
    }
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.mccourse.side_pedestal");
    }

    @Override
    public void clearContent() {
        inventory.set(0, ItemStack.EMPTY);
        setChanged();
        if(!level.isClientSide()) {
            for(ServerPlayer player : PlayerLookup.tracking((ServerLevel) level, getBlockPos())) {
                ServerPlayNetworking.send(player, new PedestalCraftPayloadS2C(this.worldPosition, inventory.get(0)));
            }
        }
    }
}
