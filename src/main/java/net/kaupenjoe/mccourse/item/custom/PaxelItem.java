package net.kaupenjoe.mccourse.item.custom;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.kaupenjoe.mccourse.tag.ModTags;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class PaxelItem extends Item {
    protected static final Map<Block, BlockState> FLATTENABLES = new ImmutableMap.Builder<Block, BlockState>()
            .put(Blocks.GRASS_BLOCK, Blocks.DIRT_PATH.defaultBlockState())
            .put(Blocks.DIRT, Blocks.DIRT_PATH.defaultBlockState())
            .put(Blocks.PODZOL, Blocks.DIRT_PATH.defaultBlockState())
            .put(Blocks.COARSE_DIRT, Blocks.DIRT_PATH.defaultBlockState())
            .put(Blocks.MYCELIUM, Blocks.DIRT_PATH.defaultBlockState())
            .put(Blocks.ROOTED_DIRT, Blocks.DIRT_PATH.defaultBlockState()).build();

    public PaxelItem(ToolMaterial toolMaterial, float attackDamageBaseline,
                     float attackSpeedBaseline, Properties properties) {
        super(properties.tool(toolMaterial, ModTags.Blocks.PAXEL_MINEABLE, attackDamageBaseline, attackSpeedBaseline, 1f));
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        Player player = pContext.getPlayer();

        if (playerHasShieldUseIntent(pContext)) {
            return InteractionResult.PASS;
        } else {
            Optional<BlockState> optional = this.tryStrip(level, blockpos, player, level.getBlockState(blockpos));
            if (optional.isEmpty()) {
                BlockState blockstate = level.getBlockState(blockpos);
                if (pContext.getClickedFace() == Direction.DOWN) {
                    return InteractionResult.PASS;
                } else {
                    BlockState blockstate1 = FLATTENABLES.get(blockstate.getBlock());
                    BlockState blockstate2 = null;
                    if (blockstate1 != null && level.getBlockState(blockpos.above()).isAir()) {
                        level.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                        blockstate2 = blockstate1;
                    } else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.getValue(CampfireBlock.LIT)) {
                        if (!level.isClientSide()) {
                            level.levelEvent(null, 1009, blockpos, 0);
                        }

                        CampfireBlock.dowse(pContext.getPlayer(), level, blockpos, blockstate);
                        blockstate2 = blockstate.setValue(CampfireBlock.LIT, Boolean.valueOf(false));
                    }

                    if (blockstate2 != null) {
                        if (!level.isClientSide()) {
                            level.setBlock(blockpos, blockstate2, 11);
                            level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, blockstate2));
                            if (player != null) {
                                pContext.getItemInHand().hurtAndBreak(1, player, pContext.getHand());
                            }
                        }

                        return InteractionResult.SUCCESS;
                    } else {
                        return InteractionResult.PASS;
                    }
                }
            } else {
                ItemStack itemstack = pContext.getItemInHand();
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockpos, itemstack);
                }

                level.setBlock(blockpos, optional.get(), 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, optional.get()));
                if (player != null) {
                    itemstack.hurtAndBreak(1, player, pContext.getHand());
                }

                return InteractionResult.SUCCESS;
            }
        }
    }

    private Optional<BlockState> tryStrip(Level world, BlockPos pos, @Nullable Player player, BlockState state) {
        Optional<BlockState> optional = this.getStripped(state);
        if (optional.isPresent()) {
            world.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f);
            return optional;
        }
        Optional<BlockState> optional2 = WeatheringCopper.getPrevious(state);
        if (optional2.isPresent()) {
            world.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0f, 1.0f);
            world.levelEvent(player, LevelEvent.PARTICLES_SCRAPE, pos, 0);
            return optional2;
        }
        Optional<BlockState> optional3 = Optional.ofNullable((Block) HoneycombItem.WAX_OFF_BY_BLOCK
                .get().get(state.getBlock())).map(block -> block.withPropertiesOf(state));
        if (optional3.isPresent()) {
            world.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0f, 1.0f);
            world.levelEvent(player, LevelEvent.PARTICLES_WAX_OFF, pos, 0);
            return optional3;
        }
        return Optional.empty();
    }

    private static boolean playerHasShieldUseIntent(UseOnContext context) {
        Player player = context.getPlayer();
        return context.getHand().equals(InteractionHand.MAIN_HAND) && player.getOffhandItem().is(Items.SHIELD) && !player.isSecondaryUseActive();
    }

    private Optional<BlockState> getStripped(BlockState unstrippedState) {
        return Optional.ofNullable(StrippableBlockRegistry.getStrippedBlockState(unstrippedState));
    }
}
