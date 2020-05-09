package com.github.ysl3000.bukkit.pathfinding.pathfinding

/**
 * Created by Yannick on 30.11.2016.
 */
interface PathfinderGoal {

    /**
     * Whether the pathfinder goal should commence execution or not
     *
     * @return true if should execute
     */
    fun shouldExecute(): Boolean

    /**
     * Whether the goal should Terminate
     *
     * @return true if should terminate
     */
    @Deprecated("Does make exactly the opposite", replaceWith = ReplaceWith("canContinueToUse"))
    fun shouldTerminate(): Boolean

    fun canContinueToUse(): Boolean {
        return !shouldTerminate();
    }
    /**
     * Runs initially and should be used to setUp goalEnvironment Condition needs to be defined thus
     * your code in it isn't called
     */
    fun init()

    /**
     * Is called when [PathfinderGoal.shouldExecute] returns true
     */
    fun execute()

    /**
     * Reset the pathfinder AI pack to its initial state
     *
     * Is called when [PathfinderGoal.shouldExecute] returns false
     */
    fun reset()
}
