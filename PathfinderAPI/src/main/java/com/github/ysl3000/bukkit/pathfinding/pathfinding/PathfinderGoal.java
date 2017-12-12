package com.github.ysl3000.bukkit.pathfinding.pathfinding;

/**
 * Created by Yannick on 30.11.2016.
 */
public interface PathfinderGoal {

    /**
     * Whether the pathfinder goal should commence execution or not
     *
     * @return true if should execute
     */
    boolean shouldExecute();

    /**
     * Whether the goal should Terminate
     *
     * @return true if should terminate
     */
    boolean shouldContinueUpdating();

    /**
     * Runs initially and should be used to setUp goalEnvironment
     * Condition needs to be defined thus your code in it isn't called
     */
    void init();

    /**
     * Is called when {@link #shouldExecute()} returns true
     *
     */
    void execute();

    /**
     * Reset the pathfinder AI pack to its initial state
     *
     * Is called when {@link #shouldExecute()} returns false
     *
     */
    void reset();
}
