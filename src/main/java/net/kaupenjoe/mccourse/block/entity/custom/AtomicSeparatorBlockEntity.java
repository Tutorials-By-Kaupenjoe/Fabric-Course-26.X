package net.kaupenjoe.mccourse.block.entity.custom;

import net.kaupenjoe.mccourse.block.custom.AtomicSeparatorBlock;
import net.kaupenjoe.mccourse.block.entity.ModBlockEntities;
import net.kaupenjoe.mccourse.menu.custom.AtomicSeparatorMenu;
import net.kaupenjoe.mccourse.recipe.ModRecipes;
import net.kaupenjoe.mccourse.recipe.custom.AtomicSeparatorRecipe;
import net.kaupenjoe.mccourse.recipe.custom.AtomicSeparatorRecipeInput;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
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
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class AtomicSeparatorBlockEntity extends BlockEntity implements MenuProvider, Container {
    public final NonNullList<ItemStack> inventory = NonNullList.withSize(4, ItemStack.EMPTY);

    private static final int OUTPUT_SLOT_1 = 0;
    private static final int OUTPUT_SLOT_2 = 1;
    private static final int OUTPUT_SLOT_3 = 2;

    private static final int INPUT_SLOT = 3;

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;
    private final int DEFAULT_MAX_PROGRESS = 72;

    public AtomicSeparatorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ATOMIC_SEPARATOR_BE, pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> AtomicSeparatorBlockEntity.this.progress;
                    case 1 -> AtomicSeparatorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0:
                        AtomicSeparatorBlockEntity.this.progress = pValue;
                    case 1:
                        AtomicSeparatorBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.mccourse.atomic_separator");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new AtomicSeparatorMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        output.putInt("atomic_separator.progress", progress);
        output.putInt("atomic_separator.max_progress", maxProgress);

        ContainerHelper.saveAllItems(output, inventory);

        // ENERGY_STORAGE.serialize(output);
        // FLUID_TANK.serialize(output);

        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        progress = input.getInt("atomic_separator.progress").get();
        maxProgress = input.getInt("atomic_separator.max_progress").get();

        ContainerHelper.loadAllItems(input, inventory);

        // itemStacksResourceHandler.deserialize(input); // Resets to 1 stack
        // ENERGY_STORAGE.deserialize(input);
        // FLUID_TANK.deserialize(input);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.size());
        for (int i = 0; i < inventory.size(); i++) {
            inv.setItem(i, inventory.get(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public void tick(Level level, BlockPos pPos, BlockState pState) {
        if (hasRecipe() && isOutputSlotEmptyOrReceivable()) {
            increaseCraftingProgress();
            level.setBlockAndUpdate(pPos, pState.setValue(AtomicSeparatorBlock.LIT, true));
            setChanged(level, pPos, pState);

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }

        } else {
            resetProgress();
            level.setBlockAndUpdate(pPos, pState.setValue(AtomicSeparatorBlock.LIT, false));
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = DEFAULT_MAX_PROGRESS;
    }

    private void craftItem() {
        Optional<RecipeHolder<AtomicSeparatorRecipe>> recipe = getCurrentRecipe();
        List<ItemStack> outputs = recipe.get().value().getOutputs();

        this.removeItem(INPUT_SLOT, 1);

        this.setItem(OUTPUT_SLOT_1, outputs.get(0).copyWithCount(this.getItem(OUTPUT_SLOT_1).count() + outputs.get(0).getCount()));
        this.setItem(OUTPUT_SLOT_2, outputs.get(1).copyWithCount(this.getItem(OUTPUT_SLOT_2).count() + outputs.get(1).getCount()));
        this.setItem(OUTPUT_SLOT_3, outputs.get(2).copyWithCount(this.getItem(OUTPUT_SLOT_3).count() + outputs.get(2).getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        for(int i = 0; i < 3; i++) {
            if(!(this.getItem(i).isEmpty() ||
                    this.getItem(i).getCount() <
                            this.getItem(i).getMaxStackSize())) {
                return false;
            }
        }

        return true;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<AtomicSeparatorRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }

        List<ItemStack> outputs = recipe.get().value().getOutputs();

        return canInsertAmountIntoOutputSlot(outputs) && canInsertItemIntoOutputSlot(outputs);
    }

    private Optional<RecipeHolder<AtomicSeparatorRecipe>> getCurrentRecipe() {
        return ((ServerLevel) this.level).recipeAccess()
                .getRecipeFor(ModRecipes.ATOMIC_SEPARATOR_TYPE,
                        new AtomicSeparatorRecipeInput(this.getItem(INPUT_SLOT)), level);
    }

    private boolean canInsertItemIntoOutputSlot(List<ItemStack> outputs) {
        for(int i = 0; i < outputs.size(); i++) {
            if(!(this.getItem(i).isEmpty() ||
                    this.getItem(i).getItem() == outputs.get(i).getItem())) {
                return false;
            }
        }
        return true;
    }

    private boolean canInsertAmountIntoOutputSlot(List<ItemStack> outputs) {
        int maxCount;
        int currentCount;

        for(int i = 0; i < outputs.size(); i++) {
            maxCount = this.getItem(i).isEmpty() ? 64 : this.getItem(i).getMaxStackSize();
            currentCount = this.getItem(i).getCount();

            if(!(maxCount >= currentCount + outputs.get(i).getCount())) {
                return false;
            }
        }
        return true;
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
    public int getContainerSize() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int count) {
        ItemStack result = ContainerHelper.removeItem(inventory, slot, count);
        if (!result.isEmpty()) {
            setChanged();
        }

        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return inventory.set(slot, ItemStack.EMPTY);
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {
        inventory.set(slot, itemStack);
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {

    }
}
