package net.kaupenjoe.mccourse.block.entity.sub;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public abstract class EnergyBlockEntity extends BlockEntity {
    public SimpleEnergyStorage ENERGY_STORAGE;

    protected SimpleEnergyStorage createEnergyStorage(int cap, int transfer, int maxExtract) {
        return new SimpleEnergyStorage(cap, transfer, maxExtract) {
            @Override
            protected void onFinalCommit() {
                setChanged();
                getLevel().sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        };
    }

    public abstract void assignEnergyStorage();

    public EnergyBlockEntity(BlockEntityType<?> type, BlockPos pPos, BlockState pBlockState) {
        super(type, pPos, pBlockState);
        this.assignEnergyStorage();
    }

    public SimpleEnergyStorage getEnergyStorage() {
        return this.ENERGY_STORAGE;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putLong("energy_block_entity.energy", ENERGY_STORAGE.amount);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ENERGY_STORAGE.amount = input.getLong("energy_block_entity.energy").get();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if(!level.isClientSide()) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }
}
