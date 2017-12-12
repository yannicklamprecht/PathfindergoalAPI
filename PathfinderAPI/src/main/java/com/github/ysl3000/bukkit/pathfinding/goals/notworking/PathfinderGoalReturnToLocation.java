package com.github.ysl3000.bukkit.pathfinding.goals.notworking;

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

/**
 * Created by Yannick on 30.11.2016.
 */
public class PathfinderGoalReturnToLocation implements PathfinderGoal {

    private final Navigation navigation;
    private Location loc;
    private final LivingEntity entity;
    private final double moveRadius;
    private Insentient pathfinderGoalEntity;
    public PathfinderGoalReturnToLocation(Insentient pathfinderGoalEntity, LivingEntity entity, double moveRadius) {
        this.loc = entity.getLocation();
        this.entity = entity;
        this.moveRadius = moveRadius;
        this.pathfinderGoalEntity = pathfinderGoalEntity;
        this.navigation = pathfinderGoalEntity.getNavigation();
    }


    public boolean shouldExecute() {
        return this.loc.distance(this.entity.getLocation()) > this.moveRadius;
    }

    @Override
    public boolean shouldContinueUpdating() {
        return !navigation.isDoneNavigating();
    }

    @Override
    public void init() {
        if (this.navigation.isDoneNavigating()) {
            this.navigation.moveTo(this.loc, 1.0D);
        }
    }

    @Override
    public void execute() {
        this.pathfinderGoalEntity.getControllerJump().jump();
    }


    public void reset() {

    }
}
