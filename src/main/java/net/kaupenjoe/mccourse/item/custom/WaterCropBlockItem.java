package net.kaupenjoe.mccourse.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;

public class WaterCropBlockItem extends BlockItem {
    public WaterCropBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    public InteractionResult useOn(UseOnContext context) {
        InteractionResult interactionResult = this.place(new BlockPlaceContext(context));
        if (context.getLevel().getBlockState(context.getClickedPos().above()).getBlock() == Blocks.WATER) {
            BlockPlaceContext newContext = new BlockPlaceContext(context.getLevel(), context.getPlayer(),
                    context.getHand(), context.getItemInHand(),
                    new BlockHitResult(context.getClickLocation(), context.getClickedFace(),
                            context.getClickedPos().relative(context.getClickedFace(), 2), true));

            interactionResult = this.place(newContext);
        }

        return !interactionResult.consumesAction() ? this.use(context.getLevel(),
                context.getPlayer(), context.getHand()) : interactionResult;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);

        if (blockHitResult.getType() == BlockHitResult.Type.MISS) {
            return InteractionResult.PASS;
        } else if (blockHitResult.getType() != BlockHitResult.Type.BLOCK) {
            return InteractionResult.PASS;
        } else {
            BlockPos hitBlockPos = blockHitResult.getBlockPos();
            Direction direction = blockHitResult.getDirection();
            BlockPos relativeBlockPos = hitBlockPos.relative(direction);

            if (level.mayInteract(player, hitBlockPos) && player.mayUseItemAt(relativeBlockPos, direction, itemstack)) {
                this.place(new BlockPlaceContext(player, hand, itemstack, new BlockHitResult(blockHitResult.getLocation(),
                        direction, relativeBlockPos, true)));
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.FAIL;
            }
        }
    }
}
