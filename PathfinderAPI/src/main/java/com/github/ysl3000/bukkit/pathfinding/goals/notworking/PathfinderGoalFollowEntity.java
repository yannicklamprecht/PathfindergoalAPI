package com.github.ysl3000.bukkit.pathfinding.goals.notworking;

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;

public class PathfinderGoalFollowEntity implements PathfinderGoal {


    private final LivingEntity entity;
    private final double moveRadius;
    private Insentient pathfinderGoalEntity;
    private double walkspeed;


    public PathfinderGoalFollowEntity(Insentient pathfinderGoalEntity, LivingEntity entity, double moveRadius, double walkspeed) {
        this.entity = entity;
        this.moveRadius = moveRadius;
        this.pathfinderGoalEntity = pathfinderGoalEntity;
        this.walkspeed = walkspeed;
    }

    @Override
    public boolean shouldExecute() {
        return !this.pathfinderGoalEntity.getNavigation().isDoneNavigating();
    }

    /**
     * Whether the goal should Terminate
     *
     * @return true if should terminate
     */
    @Override
    public boolean shouldTerminate() {
        return pathfinderGoalEntity.getNavigation().isDoneNavigating() || this.pathfinderGoalEntity.getBukkitEntity().getLocation().distance(this.entity.getLocation()) > this.moveRadius;
    }

    /**
     * Runs initially and should be used to setUp goalEnvironment
     * Condition needs to be defined thus your code in it isn't called
     */
    @Override
    public void init() {

    }

    /**
     * Is called when {@link #shouldExecute()} returns true
     */
    @Override
    public void execute() {

    }

    public void reset() {
        this.pathfinderGoalEntity.getNavigation().moveTo(this.entity, walkspeed);
    }

    public void move() {
        // TODO: 10.12.16 block detection as PathfinderGoal -> move/jump
        if (pathfinderGoalEntity.getBukkitEntity().getLocation().add(pathfinderGoalEntity.getBukkitEntity().getLocation().getDirection().normalize()).getBlock().getType() != Material.AIR) {
            this.pathfinderGoalEntity.getControllerJump().jump();
        }
    }


}