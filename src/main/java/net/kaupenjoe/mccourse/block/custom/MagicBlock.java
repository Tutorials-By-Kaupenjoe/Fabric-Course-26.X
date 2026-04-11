package net.kaupenjoe.mccourse.block.custom;

import net.kaupenjoe.mccourse.particle.ModParticles;
import net.kaupenjoe.mccourse.tag.ModTags;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MagicBlock extends Block {
    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        level.playSound(player, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1f, 1f);

        // we are on the client
        if(level.isClientSide()) {
            for(int i = 0; i < 20; i++) {
                level.addParticle(ModParticles.BISMUTH_PARTICLE,
                        pos.getX() + 0.5, pos.getY() + 1.25, pos.getZ() + 0.5, level.getRandom().nextDouble() * 20f, 1, level.getRandom().nextDouble() * 20f);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        if(entity instanceof LivingEntity livingEntity) {
            if(!livingEntity.hasEffect(MobEffects.GLOWING)) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 400));
            }
        }

        if(entity instanceof ItemEntity itemEntity) {
            if(isValidItem(itemEntity.getItem())) {
                itemEntity.setItem(new ItemStack(Items.DIAMOND, itemEntity.getItem().count()));
            }
        }

        super.stepOn(level, pos, onState, entity);
    }

    private boolean isValidItem(ItemStack item) {
        return item.is(ModTags.Items.TRANSFORMABLE_ITEMS);
    }
}
