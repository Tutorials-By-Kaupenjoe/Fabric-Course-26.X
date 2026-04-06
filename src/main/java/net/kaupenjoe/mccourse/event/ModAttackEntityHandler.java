package net.kaupenjoe.mccourse.event;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jspecify.annotations.Nullable;

public class ModAttackEntityHandler implements AttackEntityCallback {
    @Override
    public InteractionResult interact(Player player, Level level, InteractionHand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if(entity instanceof Sheep && !level.isClientSide()) {
            player.sendSystemMessage(Component.literal(player.getName().getString() + " attacked a helpless sheep!"));
        }

        return InteractionResult.PASS;
    }
}
