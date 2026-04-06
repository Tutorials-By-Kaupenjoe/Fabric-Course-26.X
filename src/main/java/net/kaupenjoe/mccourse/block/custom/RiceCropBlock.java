package net.kaupenjoe.mccourse.block.custom;

import net.kaupenjoe.mccourse.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class RiceCropBlock extends CropBlock {
    public RiceCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.RICE_SHOOT;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState stateBelow = level.getBlockState(pos.below());
        if(stateBelow.is(Blocks.WATER)) {
            BlockPos positionBelow = pos.below();

            for(Direction direction : Direction.Plane.HORIZONTAL) {
                BlockState blockState = level.getBlockState(positionBelow.relative(direction));
                FluidState fluidState = level.getFluidState(positionBelow.relative(direction));
                if(fluidState.is(FluidTags.WATER) || blockState.is(Blocks.FROSTED_ICE)) {
                    return true;
                }
            }
        }

        return false;
    }
}
