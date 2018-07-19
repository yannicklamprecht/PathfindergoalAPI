package com.github.ysl3000.bukkit.pathfinding.goals.notworking;

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;

public class PathfinderGoalFollowEntity implements PathfinderGoal {


    private final LivingEntity entity;
    private final double moveRadius;
    private Creature creature;
    private Insentient pathfinderGoalEntity;

    public PathfinderGoalFollowEntity(Insentient pathfinderGoalEntity, Creature creature, LivingEntity entity, double moveRadius) {
        this.creature = creature;
        this.entity = entity;
        this.moveRadius = moveRadius;
        this.pathfinderGoalEntity = pathfinderGoalEntity;
    }

    @Override
    public boolean shouldExecute() {
        return shouldTerminate();
    }

    @Override
    public boolean shouldTerminate() {
        return distance() >= moveRadius;
    }

    @Override
    public void init() {
        if (creature.getTarget() == null) {
            this.creature.setTarget(entity);
            pathfinderGoalEntity.getNavigation().moveTo(this.entity);
        }
    }

    @Override
    public void execute() {
        pathfinderGoalEntity.getControllerJump().jump();
        if (pathfinderGoalEntity.getNavigation().isDoneNavigating())
            pathfinderGoalEntity.getNavigation().moveTo(this.entity);
    }

    @Override
    public void reset() {
    }

    private double distance() {
        return creature.getLocation().distanceSquared(this.entity.getLocation());
    }

}
