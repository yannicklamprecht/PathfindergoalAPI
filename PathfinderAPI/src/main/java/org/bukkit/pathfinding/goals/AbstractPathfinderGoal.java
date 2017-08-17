package org.bukkit.pathfinding.goals;

import org.bukkit.pathfinding.PathfinderGoal;

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