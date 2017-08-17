package com.github.ysl3000.pathfindergoalapi.pathfinding;

/**
 * Created by Yannick on 30.11.2016.
 */
public interface PathfinderGoal {

    /**
     * returns if pathfindergoal can be interrupted by something
     *
     * @return true if it can be interrupted
     */
    boolean isInterruptible();
    /**
     * Whether the pathfinder goal should commence execution or not
     *
     * @return true if should execute
     */
    default boolean shouldExecute() {
        return shouldInitExecute();
    }
    /**
     * todo find better JD description
     * if pathfinder initialisation can be done
     *
     * @return true if initialisation can happen
     */
    boolean shouldInitExecute();
    /**
     * Called to execute the pathfinder goal. This will be called
     * once at the start of the pathfinder execution
     */
    void initExecute();
    /**
     * Called every tick to update the pathfinder AI
     */
    void executeUpdate();
    /**
     * Reset the pathfinder AI pack to its initial state
     */
    void reset();
    /**
     * todo need proper JavaDoc description
     */
    void move();
}
