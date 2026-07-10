package net.kaupenjoe.mccourse.block.entity.custom;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.kaupenjoe.mccourse.block.entity.ImplementedInventory;
import net.kaupenjoe.mccourse.block.entity.ModBlockEntities;
import net.kaupenjoe.mccourse.block.entity.sub.EnergyBlockEntity;
import net.kaupenjoe.mccourse.menu.custom.CoalGeneratorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;

public class CoalGeneratorBlockEntity extends EnergyBlockEntity implements MenuProvider, ImplementedInventory {
    public final NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;

    protected final ContainerData data;
    private int burnProgress = 160;
    private int maxBurnProgress = 160;
    private boolean isBurning = false;

    private static final int ENERGY_TRANSFER_AMOUNT = 320;

    public CoalGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.COAL_GENERATOR_BE, pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> CoalGeneratorBlockEntity.this.burnProgress;
                    case 1 -> CoalGeneratorBlockEntity.this.maxBurnProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> CoalGeneratorBlockEntity.this.burnProgress = pValue;
                    case 1 -> CoalGeneratorBlockEntity.this.maxBurnProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public void assignEnergyStorage() {
        this.ENERGY_STORAGE = createEnergyStorage(64000, 320, 320);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Coal Generator");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new CoalGeneratorMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.size());
        for (int i = 0; i < inventory.size(); i++) {
            inv.setItem(i, inventory.get(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (hasFuelItemInSlot()) {
            if (!isBurningFuel()) {
                startBurning();
            }
        }

        if (isBurningFuel()) {
            increaseBurnTimer();
            if (currentFuelDoneBurning()) {
                resetBurning();
            }
            fillUpOnEnergy();
        }

        pushEnergyToAboveNeighbour();
    }

    private void pushEnergyToAboveNeighbour() {
        try (Transaction transaction = Transaction.openOuter()) {
            EnergyStorageUtil.move(this.ENERGY_STORAGE, EnergyStorage.SIDED.find(level, worldPosition.above(), Direction.UP), ENERGY_TRANSFER_AMOUNT, transaction);
            transaction.commit();
        }
    }

    private boolean hasFuelItemInSlot() {
        return this.inventory.get(INPUT_SLOT).is(Items.COAL);
    }

    private boolean isBurningFuel() {
        return isBurning;
    }

    private void startBurning() {
        this.removeItem(INPUT_SLOT, 1);
        isBurning = true;
    }

    private void increaseBurnTimer() {
        this.burnProgress--;
    }

    private boolean currentFuelDoneBurning() {
        return this.burnProgress <= 0;
    }

    private void resetBurning() {
        isBurning = false;
        this.burnProgress = 160;
    }

    private void fillUpOnEnergy() {
        try (Transaction transaction = Transaction.openOuter()) {
            this.ENERGY_STORAGE.insert(320, transaction);
            transaction.commit();
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);

        ContainerHelper.saveAllItems(output, inventory);

        output.putInt("coal_generator.burn_progress", burnProgress);
        output.putInt("coal_generator.max_burn_progress", maxBurnProgress);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        ContainerHelper.loadAllItems(input, inventory);

        burnProgress = input.getInt("coal_generator.burn_progress").get();
        maxBurnProgress = input.getInt("coal_generator.max_burn_progress").get();
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.inventory;
    }
}
