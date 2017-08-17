package org.bukkit.pathfinding.goals;

import org.bukkit.entity.Insentient;
import org.bukkit.pathfinding.PathfinderGoal;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;

public class PathfinderGoalFollowEntity implements PathfinderGoal {


    private final LivingEntity entity;
    private final double moveRadius;
    private boolean isAlreadySet;
    private Insentient pathfinderGoalEntity;
    private double walkspeed;

    private boolean changedMoved = false;

    public PathfinderGoalFollowEntity(Insentient pathfinderGoalEntity, LivingEntity entity, double moveRadius, double walkspeed) {
        this.entity = entity;
        this.moveRadius = moveRadius;
        this.pathfinderGoalEntity = pathfinderGoalEntity;
        this.walkspeed = walkspeed;
    }

    public boolean isInterruptible() {
        return true;
    }

    public boolean shouldExecute() {
        return this.isAlreadySet = !this.pathfinderGoalEntity.getNavigation().isMovementSet();
    }

    public boolean shouldInitExecute() {
        if (this.isAlreadySet) return false;
        return this.pathfinderGoalEntity.getBukkitEntity().getLocation().distance(this.entity.getLocation()) > this.moveRadius;
    }

    public void initExecute() {
        if (!this.isAlreadySet) {
            this.pathfinderGoalEntity.getNavigation().moveTo(this.entity, walkspeed);
        }
    }

    public void executeUpdate() {

    }

    public void reset() {

    }

    public void move() {
        // TODO: 10.12.16 block detection as PathfinderGoal -> move/jump
        if (pathfinderGoalEntity.getBukkitEntity().getLocation().add(pathfinderGoalEntity.getBukkitEntity().getLocation().getDirection().normalize()).getBlock().getType() != Material.AIR) {
            this.pathfinderGoalEntity.getControllerJump().jump();
        }
    }


}
