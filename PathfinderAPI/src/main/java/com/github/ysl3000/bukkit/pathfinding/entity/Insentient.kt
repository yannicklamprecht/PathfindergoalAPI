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
     * Will return the PathfinderGoalTargetSelector
     *
     * @return targetSelector
     */
    @Deprecated("")
    fun getTargetSelector(): PathfinderGoalSelector

    /**
     * Will return the PathfinderGoalSelector
     *
     * @return selector
     */
    @Deprecated("")
    fun getGoalSelector(): PathfinderGoalSelector

    /**
     * Get the ControllerJump for jumping
     *
     * @return controllerJump to let the entity jump
     */
    fun getControllerJump(): ControllerJump

    /**
     * Get the ControllerLook for looking
     *
     * @return controllerLook to let the entity look at a target
     */
    fun getControllerLook(): ControllerLook


    /**
     * Get the controllerMove direct movement
     *
     * @return controllerMove to move the entity directly
     */
    fun getControllerMove(): ControllerMove

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
     * Will return the entities default yaw
     *
     * @return yaw
     */
    fun getDefaultYaw(): Int

    /**
     * Will return the entities default pitch
     *
     * @return pitch
     */
    fun getDefaultPitch(): Int

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
     * @param motionX relative motionX
     * @param motionY relative motionY
     * @param motionZ relative motionZ
     */
    @Deprecated("use {@link Insentient#setMovementDirection(Vector, double)} Will move the Entity\n" +
            "    relative")
    fun moveRelative(motionX: Double, motionY: Double, motionZ: Double)


    /**
     * @param motionX relative motionX
     * @param motionY relative motionY
     * @param motionZ relative motionZ
     * @param speed the speed multiplier
     */
    @Deprecated("use {@link Insentient#setMovementDirection(Vector, double)} Will move the Entity\n" +
            "    relative")
    fun moveRelative(motionX: Double, motionY: Double, motionZ: Double, speed: Double)


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
