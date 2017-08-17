package org.bukkit.pathfinding.goals;

import org.bukkit.pathfinding.Insentient;
import org.bukkit.pathfinding.PathfinderGoal;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

/**
 * Created by Yannick on 30.11.2016.
 */
public class PathfinderGoalReturnToLocation implements PathfinderGoal {

    private Location loc;
    private final LivingEntity entity;
    private final double moveRadius;
    private boolean isAlreadySet;
    private Insentient pathfinderGoalEntity;
    public PathfinderGoalReturnToLocation(Insentient pathfinderGoalEntity, LivingEntity entity, double moveRadius) {
        this.loc = entity.getLocation();
        this.entity = entity;
        this.moveRadius = moveRadius;
        this.pathfinderGoalEntity = pathfinderGoalEntity;
    }
    public boolean isInterruptible() {
        return true;
    }
    public boolean shouldExecute() {
        return this.isAlreadySet = !this.pathfinderGoalEntity.getNavigation().isMovementSet();
    }
    public boolean shouldInitExecute() {
        if (this.isAlreadySet) return false;
        return this.loc.distance(this.entity.getLocation()) > this.moveRadius;
    }
    public void initExecute() {
        if (!this.isAlreadySet) {
            this.pathfinderGoalEntity.getNavigation().moveTo(this.loc, 1.0D);
        }
    }
    public void executeUpdate() {
    }
    public void reset() {
        this.loc = this.entity.getLocation();
    }
    public void move() {
        this.pathfinderGoalEntity.getControllerJump().jump();
    }

}
