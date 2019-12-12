package com.github.ysl3000.bukkit.pathfinding.pathfinding

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient
import org.bukkit.entity.Mob

interface PathfinderManagerMob : PathfinderManager {

    /**
     * Returns a pathfinderGoalEntity from Mob 1.13+ only (Mob got added to Bukkit API in 1.13)
     *
     * @param mob entity you want to get the PathfinderGoalEntity
     * @return pathfinderGoalEntity the entity you can apply pathfindergoals on to
     */
    fun getPathfinderGoalEntity(mob: Mob): Insentient
}