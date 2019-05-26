package com.github.ysl3000.bukkit.pathfinding.pathfinding

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient
import org.bukkit.entity.*

/**
 * Created by ysl3000 on 08.12.16.
 */
interface PathfinderManager {

    /**
     * Returns a pathfinderGoalEntity from creature
     *
     * @param creature entity you want to get the PathfinderGoalEntity
     * @return pathfinderGoalEntity the entity you can apply pathfindergoals on to
     */
    @Deprecated("Forgot r of pathfinder")
    fun getPathfindeGoalEntity(creature: Creature): Insentient

    /**
     * Returns a pathfinderGoalEntity from creature
     *
     * @param creature entity you want to get the PathfinderGoalEntity
     * @return pathfinderGoalEntity the entity you can apply pathfindergoals on to
     */
    fun getPathfinderGoalEntity(creature: Creature): Insentient


    /**
     * Returns a pathfinderGoalEntity from Mob 1.13+ only (Mob got added to Bukkit API in 1.13)
     *
     * @param mob entity you want to get the PathfinderGoalEntity
     * @return pathfinderGoalEntity the entity you can apply pathfindergoals on to
     */
    fun getPathfinderGoalEntity(mob: Mob): Insentient {
        throw UnsupportedOperationException("This method is only present in Bukkit 1.13+")
    }


    /**
     * Returns a pathfinderGoalEntity from flying
     *
     * @param flying entity you want to get the PathfinderGoalEntity
     * @return pathfinderGoalEntity the entity you can apply pathfindergoals on to
     */
    fun getPathfinderGoalEntity(flying: Flying): Insentient

    /**
     * Returns a pathfinderGoalEntity from ambient
     *
     * @param ambient entity you want to get the PathfinderGoalEntity
     * @return pathfinderGoalEntity the entity you can apply pathfindergoals on to
     */
    fun getPathfinderGoalEntity(ambient: Ambient): Insentient

    /**
     * Returns a pathfinderGoalEntity from slime
     *
     * @param slime entity you want to get the PathfinderGoalEntity
     * @return pathfinderGoalEntity the entity you can apply pathfindergoals on to
     */
    fun getPathfinderGoalEntity(slime: Slime): Insentient


    /**
     * Returns a pathfinderGoalEntity from slime
     *
     * @param enderDragon entity you want to get the PathfinderGoalEntity
     * @return pathfinderGoalEntity the entity you can apply pathfindergoals on to
     */
    fun getPathfinderGoalEntity(enderDragon: EnderDragon): Insentient


    /**
     * Returns a instance of the pathfinderPlayer
     *
     * @param player the Bukkit Player
     * @return pathfinderPlayer
     */
    fun getPathfinderPlayer(player: Player): PathfinderPlayer

}
