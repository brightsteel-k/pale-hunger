package com.br1ghtsteel.palehunger.entity.ai.goal;

import com.br1ghtsteel.palehunger.entity.ai.IPaleHungerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;
import java.util.List;

public class PaleHungerGoals {
    // Sets provoked status of Pale Hunger entity when they are attacked
    public static class RevengeProvokeGoal extends RevengeGoal {
        private final IPaleHungerEntity paleHungerEntity;

        public RevengeProvokeGoal(HostileEntity paleHungerEntity, Class<?>... noRevengeTypes) {
            super(paleHungerEntity, noRevengeTypes);
            this.paleHungerEntity = (IPaleHungerEntity)paleHungerEntity;
        }

        @Override
        public void start() {
            super.start();
            this.paleHungerEntity.setProvoked(true);
            this.paleHungerEntity.setRooted(false);
        }
    }

    // Drives Pale Hunger entity to attack when they are provoked
    public static class ProvokedAttackGoal extends MeleeAttackGoal {
        private final IPaleHungerEntity paleHungerEntity;

        public ProvokedAttackGoal(HostileEntity paleHungerEntity, double speed, boolean pauseWhenMobIdle) {
            super(paleHungerEntity, speed, pauseWhenMobIdle);
            this.paleHungerEntity = (IPaleHungerEntity) paleHungerEntity;
        }

        @Override
        public boolean canStart() {
            return this.paleHungerEntity.isProvoked() && super.canStart();
        }
    }

    // Stops Pale Hunger entity's movement when a player looks at them
    public static class FreezeOnLookedAtGoal extends Goal {
        private final HostileEntity targetEntity;

        public FreezeOnLookedAtGoal(HostileEntity targetEntity) {
            this.targetEntity = targetEntity;
            this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            return isAnyPlayerStaring(targetEntity);
        }

        @Override
        public void start() {
            this.targetEntity.getNavigation().stop();
            try {
                IPaleHungerEntity paleHungerEntity = (IPaleHungerEntity)targetEntity;
                paleHungerEntity.setRooted(true);
            } catch (ClassCastException e) {

            }
        }
    }

    // Starts Pale Hunger entity's movement towards target when player is not looking at them
    public static class AttackWhenNotLookedAtGoal extends MeleeAttackGoal {
        private final HostileEntity entity;

        public AttackWhenNotLookedAtGoal(HostileEntity entity, double speed, boolean pauseWhenMobIdle) {
            super(entity, speed, pauseWhenMobIdle);
            this.entity = entity;
        }

        @Override
        public boolean canStart() {
            return !isAnyPlayerStaring(entity) && super.canStart();
        }

        @Override
        public boolean shouldContinue() {
            return !isAnyPlayerStaring(entity) && super.shouldContinue();
        }

        @Override
        public void start() {
            try {
                IPaleHungerEntity paleHungerEntity = (IPaleHungerEntity)entity;
                paleHungerEntity.setRooted(false);
            } catch (ClassCastException e) {

            }
        }
    }

    // Player vision util functions
    protected static double playerPOVThresholdHorizontal = 0.342;
    protected static double playerPOVThresholdVertical = 0.766;

    public static boolean isAnyPlayerStaring(Entity targetEntity) {
        List<? extends PlayerEntity> players = targetEntity.getWorld().getPlayers();
        for (PlayerEntity player : players) {
            if (isPlayerStaring(targetEntity, player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPlayerStaring(Entity targetEntity, PlayerEntity player) {
        ItemStack itemStack = player.getInventory().armor.get(3);
        if (itemStack.isOf(Blocks.CARVED_PUMPKIN.asItem())) {
            return false;
        } else {
            Vec3d playerRotation = player.getRotationVec(1.0F).normalize();
            Vec3d targetDirection = new Vec3d(targetEntity.getX() - player.getX(), targetEntity.getEyeY() - player.getEyeY(), targetEntity.getZ() - player.getZ());
            double d = targetDirection.length();
            targetDirection = targetDirection.normalize();
            return isInPlayerPOVHorizontal(playerRotation, targetDirection, d) && isInPlayerPOVVertical(playerRotation, targetDirection, d) && player.canSee(targetEntity);
        }
    }

    protected static boolean isInPlayerPOVHorizontal(Vec3d playerRotation, Vec3d targetDirection, double distance) {
        Vec3d horizontalPlayerRotation = playerRotation.withAxis(Direction.Axis.Y, 0);
        Vec3d horizontalTargetDirection = targetDirection.withAxis(Direction.Axis.Y, 0);
        double e = horizontalPlayerRotation.dotProduct(horizontalTargetDirection);
        return e > playerPOVThresholdHorizontal;
    }

    protected static boolean isInPlayerPOVVertical(Vec3d playerRotation, Vec3d targetDirection, double distance) {
        Vec3d verticalPlayerRotation = playerRotation.withAxis(Direction.Axis.X, 0).withAxis(Direction.Axis.Z, 1).normalize();
        Vec3d verticalTargetDirection = targetDirection.withAxis(Direction.Axis.X, 0).withAxis(Direction.Axis.Z, 1).normalize();
        double e = verticalPlayerRotation.dotProduct(verticalTargetDirection);
        return e > playerPOVThresholdVertical;
    }
}
