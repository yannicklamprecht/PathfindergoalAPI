package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R2.entity

import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R2.pathfinding.CraftNavigation
import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R2.pathfinding.CraftPathfinderGoalWrapper
import com.github.ysl3000.bukkit.pathfinding.entity.Insentient
import net.minecraft.server.v1_13_R2.EntityInsentient
import net.minecraft.server.v1_13_R2.PathfinderGoal
import net.minecraft.server.v1_13_R2.PathfinderGoalSelector
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_13_R2.entity.*
import org.bukkit.entity.*
import org.bukkit.util.Vector
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.*

/**
 * Created by ysl3000
 */
class CraftInsentient private constructor(private val handle: EntityInsentient) : Insentient {


    private val nmsGoals = HashMap<com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal, PathfinderGoal>()
    private val nmsTargetGoals = HashMap<com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal, PathfinderGoal>()

    private val navigation: com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation

    constructor(flying: Flying) : this((flying as CraftFlying).handle) {}

    init {
        this.navigation = CraftNavigation(handle.navigation)

    }


    constructor(enderDragon: EnderDragon) : this((enderDragon as CraftEnderDragon).handle) {}

    constructor(creature: Creature) : this((creature as CraftCreature).handle) {}

    constructor(ambient: Ambient) : this((ambient as CraftAmbient).handle) {}

    constructor(slime: Slime) : this((slime as CraftSlime).handle) {}

    override fun addPathfinderGoal(priority: Int,
                                   pathfinderGoal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal) {
        val goalWrapper = CraftPathfinderGoalWrapper(pathfinderGoal)
        this.nmsGoals[pathfinderGoal] = goalWrapper
        handle.goalSelector.a(priority, goalWrapper)
    }

    override fun removePathfinderGoal(
            pathfinderGoal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal) {
        if (nmsGoals.containsKey(pathfinderGoal)) {
            val nmsGoal = nmsGoals.remove(pathfinderGoal)
            handle.goalSelector.a(nmsGoal)
        }
    }

    override fun hasPathfinderGoal(
            pathfinderGoal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal): Boolean {
        return nmsGoals.containsKey(pathfinderGoal)
    }

    override fun clearPathfinderGoals() {
        handle.goalSelector = PathfinderGoalSelector(handle.getWorld().methodProfiler)
        nmsGoals.clear()
    }


    override fun addTargetPathfinderGoal(priority: Int,
                                         pathfinderGoal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal) {
        val goalWrapper = CraftPathfinderGoalWrapper(pathfinderGoal)
        this.nmsTargetGoals[pathfinderGoal] = goalWrapper
        handle.targetSelector.a(priority, goalWrapper)
    }

    override fun removeTargetPathfinderGoal(
            pathfinderGoal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal) {
        if (nmsTargetGoals.containsKey(pathfinderGoal)) {
            val nmsGoal = nmsTargetGoals.remove(pathfinderGoal)
            handle.goalSelector.a(nmsGoal)
        }
    }

    override fun hasTargetPathfinderGoal(
            pathfinderGoal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal): Boolean {
        return nmsTargetGoals.containsKey(pathfinderGoal)
    }

    override fun clearTargetPathfinderGoals() {
        handle.targetSelector = PathfinderGoalSelector(handle.getWorld().methodProfiler)
        nmsTargetGoals.clear()
    }

    override fun jump() {
        handle.controllerJump.a()
    }

    /**
     * The entity will look to the given location
     *
     * @param location the entity should look to
     */
    override fun lookAt(location: Location) {
        handle.controllerLook
                .a(location.x, location.y, location.z, location.yaw,
                        location.pitch)
    }

    /**
     * The entity will look to the given entity
     *
     * @param entity the entity which is targeted with eyes
     */
    override fun lookAt(entity: Entity) {
        lookAt(entity.location)
    }

    override fun getLookingAt(): Location {
        val controllerLook = handle.controllerLook
        return Location(handle.bukkitEntity.world, controllerLook.e(), controllerLook.f(),
                controllerLook.g())
    }

    override fun setMovementDirection(direction: Vector, speed: Double) {
        handle.controllerMove.a(direction.x, direction.blockY.toDouble(), direction.z, speed)
    }

    override fun setStrafeDirection(forward: Float, sideward: Float) {
        handle.controllerMove.a(forward, sideward)
    }

    override fun getTargetSelector(): com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector {
        return TargetGoalSelector()
    }

    override fun getGoalSelector(): com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector {
        return GoalSelector()
    }

    override fun resetGoalsToDefault() {
        if (reset == null) {
            return
        }
        try {
            reset!!.invoke(handle)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }

    }

    override fun getControllerJump(): com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerJump {
        return ControllerJump()
    }

    override fun getControllerLook(): com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerLook {
        return ControllerLookImpl()
    }

    override fun getControllerMove(): com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerMove {
        return ControllerMove()
    }

    override fun getNavigation(): com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation {
        return navigation
    }

    override fun getHeadHeight(): Float {
        return handle.headHeight
    }

    override fun getDefaultYaw(): Int {
        return handle.L()
    }

    override fun getDefaultPitch(): Int {
        return handle.K()
    }

    override fun hasPositionChanged(): Boolean {
        return handle.positionChanged
    }

    override fun onEntityKill(livingEntity: LivingEntity) {
        handle.b((livingEntity as CraftLivingEntity).handle)
    }

    override fun getBukkitEntity(): Entity {
        return handle.bukkitEntity
    }

    override fun moveRelative(motionX: Double, motionY: Double, motionZ: Double) {
        moveRelative(motionX, motionY, motionZ, 1.0)
    }

    override fun moveRelative(motionX: Double, motionY: Double, motionZ: Double, speed: Double) {
        this.handle.motX += motionX * speed
        this.handle.motY += motionY * speed
        this.handle.motZ += motionZ * speed
        this.handle.recalcPosition()
    }

    override fun setRotation(yaw: Float, pitch: Float) {
        this.handle.yaw = yaw
        this.handle.pitch = pitch
    }

    override fun updateRenderAngles() {
        handle.controllerMove.a()
    }


    private inner class GoalSelector : com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector {

        /**
         * Add a pathfinder goal to the entity
         *
         * @param priority the priority 0 highest
         * @param goal The goal to add
         */
        override fun addPathfinderGoal(priority: Int,
                                       goal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal) {
            this@CraftInsentient.addPathfinderGoal(priority, goal)
        }

        /**
         * Remove a pathfinder goal from the entity
         *
         * @param goal The goal to remove
         */
        override fun removePathfinderGoal(
                goal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal) {
            this@CraftInsentient.removePathfinderGoal(goal)
        }

        /**
         * Will clear all goals
         */
        override fun clearGoals() {
            this@CraftInsentient.clearPathfinderGoals()
        }
    }

    private inner class TargetGoalSelector : com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector {

        /**
         * Add a pathfinder goal to the entity
         *
         * @param priority the priority 0 highest
         * @param goal The goal to add
         */
        override fun addPathfinderGoal(priority: Int,
                                       goal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal) {
            this@CraftInsentient.addTargetPathfinderGoal(priority, goal)
        }

        /**
         * Remove a pathfinder goal from the entity
         *
         * @param goal The goal to remove
         */
        override fun removePathfinderGoal(
                goal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal) {
            this@CraftInsentient.removeTargetPathfinderGoal(goal)
        }

        /**
         * Will clear all goals
         */
        override fun clearGoals() {
            this@CraftInsentient.clearTargetPathfinderGoals()
        }
    }

    private inner class ControllerJump : com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerJump {

        /**
         * Lets the entity jump
         */
        override fun jump() {
            this@CraftInsentient.jump()
        }
    }

    private inner class ControllerMove : com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerMove {

        override fun isOperationMove(): Boolean {
            return handle.controllerMove.b()
        }

        override fun move(motionX: Double, motionY: Double, motionZ: Double, speed: Double) {
            this@CraftInsentient.setMovementDirection(Vector(motionX, motionY, motionZ), speed)
        }

        override fun move(forward: Float, sideward: Float) {
            this@CraftInsentient.setStrafeDirection(forward, sideward)
        }

        override fun update() {
            handle.controllerMove.a()
        }

        override fun getX(): Double {
            return handle.controllerMove.c()
        }

        override fun getY(): Double {
            return handle.controllerMove.d()
        }

        override fun getZ(): Double {
            return handle.controllerMove.e()
        }

        override fun getSpeed(): Double {
            return handle.controllerMove.f()
        }
    }

    private inner class ControllerLookImpl : com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerLook {

        /**
         * The entity will look to the given coordinates
         *
         * @param x x-location
         * @param y y-location
         * @param z z-location
         * @param yaw head rotation yaw
         * @param pitch head rotation pitch
         */
        override fun lookAt(x: Double, y: Double, z: Double, yaw: Float, pitch: Float) {
            this@CraftInsentient
                    .lookAt(Location(handle.bukkitEntity.world, x, y, z, yaw, pitch))
        }

        /**
         * The entity will look to the given location
         *
         * @param location the entity should look to
         */
        override fun lookAt(location: Location) {
            this@CraftInsentient.lookAt(location)
        }

        /**
         * The entity will look to the given entity
         *
         * @param entity the entity which is targeted with eyes
         * @param yaw head rotation yaw
         * @param pitch head rotation pitch
         */
        override fun lookAt(entity: Entity, yaw: Float, pitch: Float) {
            this@CraftInsentient.lookAt(entity)
        }

        /**
         * Resets the current looking
         */
        override fun reset() {
            handle.controllerLook.a()
        }

        /**
         * Checks if the looking is reset
         *
         * @return if looking is default
         */
        override fun isReset(): Boolean {
            return handle.controllerLook.b()
        }

        /**
         * Gets the X-Location the entity is looking to
         *
         * @return x-location
         */
        override fun getLocationX(): Double {
            return handle.controllerMove.c()
        }

        /**
         * Gets the Y-Location the entity is looking to
         *
         * @return y-location
         */
        override fun getLocationY(): Double {
            return handle.controllerMove.d()
        }

        /**
         * Gets the Z-Location the entity is looking to
         *
         * @return z-location
         */
        override fun getLocationZ(): Double {
            return handle.controllerMove.f()
        }
    }

    companion object {


        private var reset: Method? = null

        init {

            try {
                reset = EntityInsentient::class.java!!.getDeclaredMethod("n")
                reset!!.isAccessible = true
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            }

        }
    }

}
