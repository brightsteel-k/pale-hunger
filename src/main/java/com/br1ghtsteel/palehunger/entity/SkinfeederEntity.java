package com.br1ghtsteel.palehunger.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.List;

public class SkinfeederEntity extends ZombieEntity {

    private static final TrackedData<Boolean> PROVOKED = DataTracker.registerData(SkinfeederEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public SkinfeederEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createSkinfeederAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4F)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_ARMOR, 2.0)
                .add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new SkinfeederEntity.ProvokedAttackGoal(this, 1.0, true));
        this.goalSelector.add(2, new SkinfeederEntity.StalkAttackGoal(this, 1.0, true));
        this.goalSelector.add(3, new SkinfeederEntity.FreezeOnLookedAtGoal(this));
        this.targetSelector.add(1, new SkinfeederRevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(PROVOKED, false);
    }

    public boolean isProvoked() {
        return this.dataTracker.get(PROVOKED);
    }

    public void setProvoked() {
        this.dataTracker.set(PROVOKED, true);
    }

    boolean isAnyPlayerStaring() {
        List<? extends PlayerEntity> players = this.getWorld().getPlayers();
        for (PlayerEntity player : players) {
            if (isPlayerStaring(player)) {
                return true;
            }
        }
        return false;
    }

    boolean isPlayerStaring(PlayerEntity player) {
        ItemStack itemStack = player.getInventory().armor.get(3);
        if (itemStack.isOf(Blocks.CARVED_PUMPKIN.asItem())) {
            return false;
        } else {
            Vec3d playerRotation = player.getRotationVec(1.0F).normalize();
            Vec3d skinfeederDirection = new Vec3d(this.getX() - player.getX(), this.getEyeY() - player.getEyeY(), this.getZ() - player.getZ());
            double d = skinfeederDirection.length();
            skinfeederDirection = skinfeederDirection.normalize();
            return isInHorizontalPOV(playerRotation, skinfeederDirection, d) && isInVerticalPOV(playerRotation, skinfeederDirection, d) && player.canSee(this);
        }
    }

    @Override
    protected boolean burnsInDaylight() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_HUSK_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_HUSK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_HUSK_DEATH;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_HUSK_STEP;
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean bl = super.tryAttack(target);
        if (bl && this.getMainHandStack().isEmpty() && target instanceof LivingEntity) {
            float f = this.getWorld().getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();
            ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 140 * (int)f), this);
        }

        return bl;
    }

    @Override
    protected boolean canConvertInWater() {
        return false;
    }

    @Override
    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }

    protected static double horizontalPOVThreshold = 0.342;
    protected static double verticalPOVThreshold = 0.766;

    protected static boolean isInHorizontalPOV(Vec3d playerRotation, Vec3d skinfeederDirection, double distance) {
        Vec3d horizontalPlayerRotation = playerRotation.withAxis(Direction.Axis.Y, 0);
        Vec3d horizontalSkinfeederDirection = skinfeederDirection.withAxis(Direction.Axis.Y, 0);
        double e = horizontalPlayerRotation.dotProduct(horizontalSkinfeederDirection);
        return e > horizontalPOVThreshold;
    }

    protected static boolean isInVerticalPOV(Vec3d playerRotation, Vec3d skinfeederDirection, double distance) {
        Vec3d verticalPlayerRotation = playerRotation.withAxis(Direction.Axis.X, 0).withAxis(Direction.Axis.Z, 1).normalize();
        Vec3d verticalSkinfeederDirection = skinfeederDirection.withAxis(Direction.Axis.X, 0).withAxis(Direction.Axis.Z, 1).normalize();
        double e = verticalPlayerRotation.dotProduct(verticalSkinfeederDirection);
        return e > verticalPOVThreshold;
    }

    static class StalkAttackGoal extends MeleeAttackGoal {
        private final SkinfeederEntity skinfeeder;

        public StalkAttackGoal(SkinfeederEntity skinfeeder, double speed, boolean pauseWhenMobIdle) {
            super(skinfeeder, speed, pauseWhenMobIdle);
            this.skinfeeder = skinfeeder;
        }

        @Override
        public boolean canStart() {
            return !this.skinfeeder.isAnyPlayerStaring() && super.canStart();
        }

        @Override
        public boolean shouldContinue() {
            return !this.skinfeeder.isAnyPlayerStaring() && super.shouldContinue();
        }

        @Override
        public void start() {
            this.skinfeeder.getNavigation().stop();
        }
    }

    static class FreezeOnLookedAtGoal extends Goal {
        private final SkinfeederEntity skinfeeder;

        public FreezeOnLookedAtGoal(SkinfeederEntity skinfeeder) {
            this.skinfeeder = skinfeeder;
            this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            return this.skinfeeder.isAnyPlayerStaring();
        }

        @Override
        public void start() {
            this.skinfeeder.getNavigation().stop();
        }
    }

    static class SkinfeederRevengeGoal extends RevengeGoal {
        private final SkinfeederEntity skinfeeder;

        public SkinfeederRevengeGoal(SkinfeederEntity skinfeeder, Class<?>... noRevengeTypes) {
            super(skinfeeder, noRevengeTypes);
            this.skinfeeder = skinfeeder;
        }

        @Override
        public void start() {
            super.start();
            this.skinfeeder.setProvoked();
        }
    }

    static class ProvokedAttackGoal extends MeleeAttackGoal {
        private final SkinfeederEntity skinfeeder;

        public ProvokedAttackGoal(SkinfeederEntity skinfeeder, double speed, boolean pauseWhenMobIdle) {
            super(skinfeeder, speed, pauseWhenMobIdle);
            this.skinfeeder = skinfeeder;
        }

        @Override
        public boolean canStart() {
            return this.skinfeeder.isProvoked() && super.canStart();
        }
    }
}
