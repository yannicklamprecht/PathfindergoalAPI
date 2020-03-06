package com.github.ysl3000.bukkit.pathfinding

import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderManager

object PathfinderGoalAPI {


    private var api: PathfinderManager? = null

    @JvmStatic
    fun getAPI(): PathfinderManager? {
        return api
    }

    fun setApi(pathfinderManager: PathfinderManager) {
        if (api != null) {
            throw UnsupportedOperationException("Cannot redefine singleton Server")
        } else {
            api = pathfinderManager
        }
    }

}
