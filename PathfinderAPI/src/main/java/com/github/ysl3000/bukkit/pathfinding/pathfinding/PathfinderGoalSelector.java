package com.github.ysl3000.bukkit.pathfinding.pathfinding;

/**
 * Created by Yannick on 30.11.2016.
 */
public interface PathfinderGoalSelector {

  /**
   * Add a pathfinder goal to the entity
   *
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
   * Will clear all goals
   */
  void clearGoals();


}
