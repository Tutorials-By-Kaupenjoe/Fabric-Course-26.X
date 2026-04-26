package net.kaupenjoe.mccourse.block.entity.custom;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.kaupenjoe.mccourse.block.custom.CrystallizerBlock;
import net.kaupenjoe.mccourse.block.entity.ImplementedInventory;
import net.kaupenjoe.mccourse.block.entity.ModBlockEntities;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.menu.custom.CrystallizerMenu;
import net.kaupenjoe.mccourse.menu.custom.PedestalMenu;
import net.kaupenjoe.mccourse.recipe.ModRecipes;
import net.kaupenjoe.mccourse.recipe.custom.CrystallizerRecipe;
import net.kaupenjoe.mccourse.recipe.custom.CrystallizerRecipeInput;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import java.util.Optional;

public class CrystallizerBlockEntity extends BlockEntity implements ExtendedMenuProvider<BlockPos>, ImplementedInventory {
    public final NonNullList<ItemStack> inventory = NonNullList.withSize(4, ItemStack.EMPTY);

    private static final int FLUID_ITEM_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int ENERGY_ITEM_SLOT = 3;

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    private static final int ENERGY_CRAFT_AMOUNT = 25; // amount per TICK to craft

    private final SimpleEnergyStorage ENERGY_STORAGE = new SimpleEnergyStorage(64000, 320, 25) {
        @Override
        protected void onFinalCommit() {
            setChanged(level, getBlockPos(), getBlockState());
            getLevel().sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    };


    public CrystallizerBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.CRYSTALLIZER_BE, worldPosition, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int dataId) {
                return switch (dataId) {
                    case 0 -> CrystallizerBlockEntity.this.progress;
                    case 1 -> CrystallizerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case 0: CrystallizerBlockEntity.this.progress = value;
                    case 1: CrystallizerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    /* INVENTORY */
    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("crystallizer.progress", progress);
        output.putInt("crystallizer.max_progress", maxProgress);
        output.putLong("crystallizer.energy", ENERGY_STORAGE.amount);

        ContainerHelper.saveAllItems(output, inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        progress = input.getIntOr("crystallizer.progress", 0);
        maxProgress = input.getIntOr("crystallizer.max_progress", 72);
        ENERGY_STORAGE.amount = input.getLongOr("crystallizer.energy", 0);

        ContainerHelper.loadAllItems(input, inventory);
    }

    public void drops() {
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    /* MENU PROVIDER */
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.mccourse.crystallizer");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new CrystallizerMenu(containerId, inventory, this, this.data);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return this.worldPosition;
    }

    /* TICK */
    public void tick(Level level, BlockPos pos, BlockState state) {
        if(hasRecipe() && isOutputSlotEmptyOrReceivable()) {
            increaseCraftingProgress();
            useEnergyForCrafting();
            level.setBlockAndUpdate(pos, state.setValue(CrystallizerBlock.LIT, true));
            setChanged(level, pos, state);

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }

        } else {
            resetProgress();
            level.setBlockAndUpdate(pos, state.setValue(CrystallizerBlock.LIT, false));
        }


        // Debug-ish purposes!
        if(hasItemInEnergyItemSlot()) {
            fillUpOnEnergy();
            setChanged(level, pos, state);
        }
    }

    private void craftItem() {
        Optional<RecipeHolder<CrystallizerRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output().create();

        inventory.set(INPUT_SLOT, inventory.get(INPUT_SLOT).copyWithCount(inventory.get(INPUT_SLOT).getCount() - 1));
        inventory.set(OUTPUT_SLOT, output.copyWithCount(inventory.get(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return inventory.get(OUTPUT_SLOT).isEmpty() ||
                inventory.get(OUTPUT_SLOT).getCount() < inventory.get(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<CrystallizerRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().assemble(new CrystallizerRecipeInput(inventory.get(INPUT_SLOT)));
        boolean isItemRight = canInsertItemIntoOutputSlot(output);
        boolean isAmountRight = canInsertAmountIntoOutputSlot(output.getCount());
        boolean hasEnergy = hasEnoughEnergyToCraft();

        return isItemRight && isAmountRight && hasEnergy;
    }

    private Optional<RecipeHolder<CrystallizerRecipe>> getCurrentRecipe() {
        return ((ServerLevel) level).recipeAccess()
                .getRecipeFor(ModRecipes.CRYSTALLIZER_TYPE, new CrystallizerRecipeInput(inventory.get(INPUT_SLOT)), level);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = inventory.get(OUTPUT_SLOT).isEmpty() ? 64 : inventory.get(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = inventory.get(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inventory.get(OUTPUT_SLOT).isEmpty() ||
                inventory.get(OUTPUT_SLOT).is(output.getItem());
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private void resetProgress() {
        progress = 0;
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    /* SIDED INVENTORY */
    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction side) {
        if (side == null || side == Direction.DOWN) return false;
        if (side == Direction.UP) return slot == INPUT_SLOT;

        if (slot != INPUT_SLOT && slot != FLUID_ITEM_SLOT && slot != ENERGY_ITEM_SLOT) {
            return false;
        }

        Direction localDir = this.level.getBlockState(this.getBlockPos()).getValue(CrystallizerBlock.FACING);
        return switch (slot) {
            case INPUT_SLOT -> side != localDir.getOpposite(); // If not Up/Down/Back, it must be Front/Left/Right
            case FLUID_ITEM_SLOT -> side == localDir.getClockWise(); // Left
            case ENERGY_ITEM_SLOT -> side == localDir.getCounterClockWise(); // Right
            default -> false; // Fallback
        };
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction side) {
        if (slot != OUTPUT_SLOT || side == Direction.UP) {
            return false;
        }

        if (side == Direction.DOWN) {
            return true;
        }

        Direction localDir = this.level.getBlockState(this.getBlockPos()).getValue(CrystallizerBlock.FACING);
        return side == localDir.getOpposite() || side == localDir.getClockWise();
    }

    /* ENERGY HANDLING */
    public SimpleEnergyStorage getEnergyStorage() {
        return this.ENERGY_STORAGE;
    }

    private boolean hasEnoughEnergyToCraft() {
        return ENERGY_STORAGE.amount >= (long) ENERGY_CRAFT_AMOUNT * maxProgress;
    }

    private void fillUpOnEnergy() {
        try(Transaction transaction = Transaction.openOuter()) {
            ENERGY_STORAGE.insert(160, transaction);
            transaction.commit();
        }
    }

    private boolean hasItemInEnergyItemSlot() {
        return inventory.get(ENERGY_ITEM_SLOT).is(ModItems.CAULIFLOWER);
    }

    private void useEnergyForCrafting() {
        try(Transaction transaction = Transaction.openOuter()) {
            ENERGY_STORAGE.extract(ENERGY_CRAFT_AMOUNT, transaction);
            transaction.commit();
        }
    }


    /* BLOCK ENTITY SYNC METHOD */
    @Override
    public void setChanged() {
        super.setChanged();
        if(!level.isClientSide()) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
