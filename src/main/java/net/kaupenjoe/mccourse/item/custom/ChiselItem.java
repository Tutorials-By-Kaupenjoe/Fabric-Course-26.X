package net.kaupenjoe.mccourse.item.custom;

import net.kaupenjoe.mccourse.attachment.ModAttachmentTypes;
import net.kaupenjoe.mccourse.attachment.handler.ManaHandler;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.data.ModDataComponents;
import net.kaupenjoe.mccourse.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.function.Consumer;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.GOLD_BLOCK, ModBlocks.BISMUTH_BLOCK,
                    ModBlocks.BISMUTH_BLOCK, Blocks.GOLD_BLOCK
            );

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock)) {
            // Server --> Any changes that are gameplay important
            // Giving Items/Changing Blocks/Breaking Blocks...

            // Client --> Anything (purely) Visuals

            if(!ManaHandler.hasPlayerManaLeft(context.getPlayer())) {
                context.getPlayer().sendSystemMessage(Component.literal("Not enough Mana left!"));
            }

            // This means NOT on the clientSide
            if(!level.isClientSide() && ManaHandler.hasPlayerManaLeft(context.getPlayer())) {
                level.setBlock(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState(), 3);
                level.playSound(null, context.getClickedPos(), ModSounds.CHISEL_USE, SoundSource.BLOCKS, 2.5f, 1f);

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) context.getPlayer()),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                context.getItemInHand().set(ModDataComponents.COORDINATES, context.getClickedPos());
                ManaHandler.removeMana(((ServerPlayer) context.getPlayer()), 1);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display,
                                Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if(Minecraft.getInstance().hasShiftDown()) {
            builder.accept(Component.translatable("tooltip.mccourse.chisel.shift_down"));
        } else {
            builder.accept(Component.translatable("tooltip.mccourse.chisel"));
        }

        if(itemStack.get(ModDataComponents.COORDINATES) != null) {
            builder.accept(Component.literal("Last Block Changed at " + itemStack.get(ModDataComponents.COORDINATES)));
        }

        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
