package net.kaupenjoe.mccourse.block.entity.custom;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.kaupenjoe.mccourse.block.entity.ModBlockEntities;
import net.kaupenjoe.mccourse.block.entity.sub.EnergyBlockEntity;
import net.kaupenjoe.mccourse.menu.custom.BatteryMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;

public class BatteryBlockEntity extends EnergyBlockEntity implements MenuProvider {
    public BatteryBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.BATTERY_BE, pPos, pBlockState);
    }

    @Override
    public void assignEnergyStorage() {
        this.ENERGY_STORAGE = createEnergyStorage(6400000, 6400, 6400);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Battery");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new BatteryMenu(pContainerId, pPlayerInventory, this);
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        pushEnergyToNeighbourAbove();
    }

    private void pushEnergyToNeighbourAbove() {
        try (Transaction transaction = Transaction.openOuter()) {
            EnergyStorageUtil.move(this.ENERGY_STORAGE, EnergyStorage.SIDED.find(level, worldPosition.above(), Direction.UP), 6400, transaction);
            transaction.commit();
        }
    }
}
