package org.bukkit.craftbukkit.v1_12_R1.pathfinding;

import net.minecraft.server.v1_12_R1.MethodProfiler;
import net.minecraft.server.v1_12_R1.PathfinderGoalSelector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by ysl3000
 */
public class CraftPathfinderGoalSelector implements org.bukkit.pathfinding.PathfinderGoalSelector {


    private MethodProfiler methodProfiler;
    private PathfinderGoalSelector goalSelector;
    private Map<org.bukkit.pathfinding.PathfinderGoal, net.minecraft.server.v1_12_R1.PathfinderGoal> nmsGoals = new HashMap<>();

    public CraftPathfinderGoalSelector(MethodProfiler methodProfiler, PathfinderGoalSelector goalSelector) {
        this.methodProfiler = methodProfiler;
        this.goalSelector = goalSelector;
    }

    /**
     * Add a pathfinder goal to the entity
     *
     * @param priority
     * @param goal     The goal to add
     */
    @Override
    public void addPathfinderGoal(int priority, org.bukkit.pathfinding.PathfinderGoal goal) {

        CraftPathfinderGoalWrapper goalWrapper = new CraftPathfinderGoalWrapper(goal);
        this.nmsGoals.put(goal, goalWrapper);
        addPathfinderGoal(priority, goalWrapper);
    }

    /**
     * Remove a pathfinder goal from the entity
     *
     * @param goal The goal to remove
     */
    @Override
    public void removePathfinderGoal(org.bukkit.pathfinding.PathfinderGoal goal) {
        if (nmsGoals.containsKey(goal)) {
            net.minecraft.server.v1_12_R1.PathfinderGoal nmsGoal = nmsGoals.remove(goal);
            removePathfinderGoal(nmsGoal);
        }

    }

    protected void removePathfinderGoal(net.minecraft.server.v1_12_R1.PathfinderGoal nmsGoal) {
        goalSelector.a(nmsGoal);
    }

    /**
     * Will reinitialize the entites pathfindergoals added
     */
    @Override
    public void setupGoals() {
        goalSelector.a();
    }

    /**
     * Get all pathfinder goals active on this entity
     *
     * @return All available pathfinder goals for the entity
     */
    @Override
    public Set<org.bukkit.pathfinding.PathfinderGoal> getPathfinderGoals() {
        return new HashSet<>(this.nmsGoals.keySet());
    }

    /**
     * Get a specific pathfinder goal instance from this entity
     *
     * @param goal The class of the goal to get
     * @return an instance of the goal
     */
    @Override
    public Set<org.bukkit.pathfinding.PathfinderGoal> getPathfinderGoal(Class<? extends org.bukkit.pathfinding.PathfinderGoal> goal) {
        return new HashSet<>(this.nmsGoals.keySet().stream().filter(g -> g.getClass().equals(goal)).collect(Collectors.toList()));
    }

    @Override
    public void clearGoals() {
        this.goalSelector = new PathfinderGoalSelector(methodProfiler);
        nmsGoals.clear();
    }

    private void addPathfinderGoal(int priority, net.minecraft.server.v1_12_R1.PathfinderGoal nmsGoal) {
        goalSelector.a(priority, nmsGoal);
    }

}
