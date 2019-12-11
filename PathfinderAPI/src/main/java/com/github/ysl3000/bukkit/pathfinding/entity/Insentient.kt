package com.github.ysl3000.bukkit.pathfinding.entity

import com.github.ysl3000.bukkit.pathfinding.pathfinding.*
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.util.Vector

/**
 * Created by Yannick on 30.11.2016.
 */
interface Insentient {


    fun getLookingAt(): Location


    /**
     * Get the Navigation for moving Entity
     *
     * @return navigation to control movements
     */
    fun getNavigation(): Navigation


    /**
     * Get the entities headHeight
     *
     * @return headHeight
     */
    fun getHeadHeight(): Float

    /**
     * Will return the LivingEntity of Bukkit
     *
     * @return entity
     */
    fun getBukkitEntity(): Entity


    fun addPathfinderGoal(priority: Int, pathfinderGoal: PathfinderGoal)

    fun removePathfinderGoal(pathfinderGoal: PathfinderGoal)

    fun hasPathfinderGoal(pathfinderGoal: PathfinderGoal): Boolean

    fun clearPathfinderGoals()

    fun addTargetPathfinderGoal(priority: Int, pathfinderGoal: PathfinderGoal)

    fun removeTargetPathfinderGoal(pathfinderGoal: PathfinderGoal)

    fun hasTargetPathfinderGoal(pathfinderGoal: PathfinderGoal): Boolean

    fun clearTargetPathfinderGoals()


    fun jump()


    /**
     * The entity will look to the given location
     *
     * @param location the entity should look to
     */
    fun lookAt(location: Location)

    /**
     * The entity will look to the given entity
     *
     * @param entity the entity which is targeted with eyes
     */
    fun lookAt(entity: Entity)


    fun setMovementDirection(direction: Vector, speed: Double)

    fun setStrafeDirection(forward: Float, sideward: Float)

    /**
     * Will reset goals to default one
     */
    fun resetGoalsToDefault()


    /**
     * Will return if entity changed position
     *
     * @return true if positionchange happened after last call
     */
    fun hasPositionChanged(): Boolean

    /**
     * method gets called when the entity kills another entity
     *
     * @param livingEntity the other entity
     */
    fun onEntityKill(livingEntity: LivingEntity)


    /**
     * Rotates the entity
     *
     * @param yaw absolute yaw
     * @param pitch absolute pitch
     */
    fun setRotation(yaw: Float, pitch: Float)


    /**
     * Updates the the angles for rendering
     */
    fun updateRenderAngles()
}
