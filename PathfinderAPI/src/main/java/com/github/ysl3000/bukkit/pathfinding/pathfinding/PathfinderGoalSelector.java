package com.github.ysl3000.bukkit.pathfinding.pathfinding;

import java.util.Set;

/**
 * Created by Yannick on 30.11.2016.
 */
public interface PathfinderGoalSelector {

    /**
     * Add a pathfinder goal to the entity
     * @param priority the priority 0 highest
     * @param goal The goal to add
     */
    void addPathfinderGoal(int priority, PathfinderGoal goal);
    /**
     * Remove a pathfinder goal from the entity
     *
     * @param goal The goal to remove
     */
    void removePathfinderGoal(PathfinderGoal goal);
    /**
     * Will reinitialize the entites pathfindergoals added
     */
    void setupGoals();
    /**
     * Get all pathfinder goals active on this entity
     *
     * @return All available pathfinder goals for the entity
     */
    Set<PathfinderGoal> getPathfinderGoals();
    /**
     * Get a specific pathfinder goal instance from this entity
     *
     * @param goal The class of the goal to get
     * @return goals a list of the goal instances
     */
    Set<PathfinderGoal> getPathfinderGoal(Class<? extends PathfinderGoal> goal);
    /**
     * Will clear all goals
     */
    void clearGoals();



}
