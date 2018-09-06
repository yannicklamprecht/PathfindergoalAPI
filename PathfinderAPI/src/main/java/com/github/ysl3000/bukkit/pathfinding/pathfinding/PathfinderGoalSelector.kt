package com.github.ysl3000.bukkit.pathfinding.pathfinding

/**
 * Created by Yannick on 30.11.2016.
 */
interface PathfinderGoalSelector {

    /**
     * Add a pathfinder goal to the entity
     *
     * @param priority the priority 0 highest
     * @param goal The goal to add
     */
    fun addPathfinderGoal(priority: Int, goal: PathfinderGoal)

    /**
     * Remove a pathfinder goal from the entity
     *
     * @param goal The goal to remove
     */
    fun removePathfinderGoal(goal: PathfinderGoal)

    /**
     * Will clear all goals
     */
    fun clearGoals()


}
