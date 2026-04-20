package net.kaupenjoe.mccourse.entity.custom;

import net.kaupenjoe.mccourse.entity.variant.GiraffeVariant;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Util;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class GiraffeEntity extends PathfinderMob {
    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(GiraffeEntity.class, EntityDataSerializers.INT);

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

    /* VARIANT */

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(VARIANT, 0);
    }

    private int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    public GiraffeVariant getVariant() {
        return GiraffeVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(GiraffeVariant variant) {
        this.entityData.set(VARIANT, variant.getId() & 255);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("Variant", this.getTypeVariant());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.entityData.set(VARIANT, input.getIntOr("Variant", 0));
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                                  EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        GiraffeVariant variant = Util.getRandom(GiraffeVariant.values(), this.random);
        this.setVariant(variant);
        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }
}
