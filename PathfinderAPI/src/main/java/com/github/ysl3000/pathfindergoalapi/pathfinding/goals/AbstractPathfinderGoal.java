package com.github.ysl3000.pathfindergoalapi.pathfinding.goals;

import com.github.ysl3000.pathfindergoalapi.pathfinding.PathfinderGoal;

/**
 * Created by Yannick on 30.11.2016.
 */
public abstract class AbstractPathfinderGoal implements PathfinderGoal {

    @Override
    public boolean isInterruptible() {
        return true;
    }
    @Override
    public boolean shouldInitExecute() {
        return false;
    }
    @Override
    public void initExecute() {
    }
    @Override
    public void executeUpdate() {
    }
    @Override
    public void reset() {
    }
    @Override
    public void move() {
    }

}