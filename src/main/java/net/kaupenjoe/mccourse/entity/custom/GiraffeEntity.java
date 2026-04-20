package net.kaupenjoe.mccourse.entity.custom;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class GiraffeEntity extends PathfinderMob {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public GiraffeEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new PanicGoal(this, 2d));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25d, stack -> stack.is(ItemTags.LEAVES), false));

        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1f));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6f));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 30;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    public static AttributeSupplier.Builder createGiraffeAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 16d)
                .add(Attributes.MOVEMENT_SPEED, 0.35d)
                .add(Attributes.TEMPT_RANGE, 10d)
                .add(Attributes.FOLLOW_RANGE, 24d);
    }
}
