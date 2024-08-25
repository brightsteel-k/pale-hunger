package com.br1ghtsteel.palehunger.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;

public class SkinfeederEntity extends ZombieEntity {

    public SkinfeederEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createSkinfeederAttributes() {
        return ZombieEntity.createZombieAttributes();
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(2, new SkinfeederEntity.StalkAttackGoal(this, 1.0, true));
        this.goalSelector.add(3, new SkinfeederEntity.FreezeOnLookedAtGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new RevengeGoal(this));
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
            Vec3d vec3d = player.getRotationVec(1.0F).normalize();
            Vec3d vec3d2 = new Vec3d(this.getX() - player.getX(), this.getEyeY() - player.getEyeY(), this.getZ() - player.getZ());
            double d = vec3d2.length();
            vec3d2 = vec3d2.normalize();
            double e = vec3d.dotProduct(vec3d2);
            // return e > 1.0 - 0.025 / d && player.canSee(this);
            return player.canSee(this);
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

    static class StalkAttackGoal extends MeleeAttackGoal {
        private final SkinfeederEntity skinfeeder;
        @Nullable
        private LivingEntity target;

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
}
