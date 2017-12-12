package com.github.ysl3000.bukkit.pathfinding;

import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderManager;

public class PathfinderGoalAPI {


    private static PathfinderManager api;

    private PathfinderGoalAPI() {
    }

    public static PathfinderManager getAPI() {
        return api;
    }

    public static void setApi(PathfinderManager quantumConnectors) {
        if (PathfinderGoalAPI.api != null) {
            throw new UnsupportedOperationException("Cannot redefine singleton Server");
        } else {
            PathfinderGoalAPI.api = quantumConnectors;
        }
    }

}
