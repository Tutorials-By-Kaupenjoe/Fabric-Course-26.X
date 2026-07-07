package net.kaupenjoe.mccourse.block.entity.custom;

import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.block.entity.ModBlockEntities;
import net.kaupenjoe.mccourse.recipe.ModRecipes;
import net.kaupenjoe.mccourse.recipe.custom.PedestalRecipe;
import net.kaupenjoe.mccourse.recipe.custom.PedestalRecipeInput;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector2i;

import java.util.List;
import java.util.Optional;

public class MainPedestalBlockEntity extends PedestalBlockEntity {
    public static List<Vector2i> offsets = List.of(
            new Vector2i(3, 0),
            new Vector2i(2, 2),
            new Vector2i(0, 3),
            new Vector2i(-2, 2),

            new Vector2i(-3, 0),
            new Vector2i(-2, -2),
            new Vector2i(0, -3),
            new Vector2i(2, -2));
    public int count = 0;
    private int maxCount = 60; // 3 seconds

    public MainPedestalBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.MAIN_PEDESTAL_BE, worldPosition, blockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.mccourse.main_pedestal");
    }

    /* PEDESTAL CRAFTING */
    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (!hasRecipe())
            return;

        if (countFinished()) {
            count = 0;
            exchangeItemInMainPedestal();
            removeItemsFromSidePedestals();
            spawnVisualLightningBolt((ServerLevel) level, blockPos);
            spawnExplosionParticles((ServerLevel) level);
        } else {
            countUp();
            spawnCraftingParticles(level);
        }
    }

    private void countUp() {
        count++;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean countFinished() {
        return count >= maxCount;
    }

    private void spawnVisualLightningBolt(ServerLevel level, BlockPos blockPos) {
        EntityTypes.LIGHTNING_BOLT.spawn(level, blockPos, EntitySpawnReason.TRIGGERED).setVisualOnly(true);
    }

    private void spawnExplosionParticles(ServerLevel level) {
        double x = this.getBlockPos().getX();
        double y = this.getBlockPos().getY();
        double z = this.getBlockPos().getZ();
        level.sendParticles(ParticleTypes.EXPLOSION_EMITTER,
                x + 0.5f, y + 1.2f, z + 0.5f, 0, 0, 0, 0, 0.25f);
    }

    private void exchangeItemInMainPedestal() {
        Optional<RecipeHolder<PedestalRecipe>> recipe = getCurrentRecipe();

        recipe.ifPresent(pedestalRecipeRecipeHolder ->
                this.setTheItem(pedestalRecipeRecipeHolder.value().assemble(null)));
    }

    private void removeItemsFromSidePedestals() {
        offsets.forEach(offset -> ((SidePedestalBlockEntity) level.getBlockEntity(this.getBlockPos().offset(offset.x, 0, offset.y))).clearContent());
    }

    public boolean hasRecipe() {
        Optional<RecipeHolder<PedestalRecipe>> recipe = getCurrentRecipe();
        return recipe.isPresent();
    }

    private Optional<RecipeHolder<PedestalRecipe>> getCurrentRecipe() {
        return ((ServerLevel) this.level).recipeAccess()
                .getRecipeFor(ModRecipes.PEDESTAL_TYPE, new PedestalRecipeInput(
                        this.inventory.get(0),
                        offsets.stream().map(offset -> {
                            if (hasSidePedestals()) {
                                return ((SidePedestalBlockEntity) level.getBlockEntity(this.getBlockPos().offset(offset.x, 0, offset.y))).
                                        inventory.get(0);
                            } else {
                                return ItemStack.EMPTY;
                            }
                        }).toList()), level);
    }

    private boolean hasSidePedestals() {
        return offsets.stream().allMatch(this::isSidePedestal);
    }

    private boolean isSidePedestal(Vector2i offset) {
        return level.getBlockState(this.getBlockPos().offset(offset.x, 0, offset.y)).is(ModBlocks.SIDE_PEDESTAL);
    }

    private void spawnCraftingParticles(Level level) {
        offsets.forEach(offset -> {
            ItemStack stack = ((SidePedestalBlockEntity) level.getBlockEntity(this.getBlockPos().offset(offset.x, 0, offset.y)))
                    .inventory.get(0);
            double x = this.getBlockPos().offset(offset.x, 0, offset.y).getX();
            double y = this.getBlockPos().offset(offset.x, 0, offset.y).getY();
            double z = this.getBlockPos().offset(offset.x, 0, offset.y).getZ();

            BlockPos direction = getBlockPos().subtract(this.getBlockPos().offset(offset.x, 0, offset.y));

            ((ServerLevel) level).sendParticles(new ItemParticleOption
                    (ParticleTypes.ITEM, stack.getItem()), x + 0.5f, y + 1.2f, z + 0.5f, 0, direction.getX(), direction.getY(), direction.getZ(), 0.25f);
        });
    }
}
