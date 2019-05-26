package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.entity

import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.pathfinding.CraftNavigation
import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.pathfinding.CraftPathfinderGoalWrapper
import com.github.ysl3000.bukkit.pathfinding.entity.Insentient
import com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerLook
import com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation
import net.minecraft.server.v1_13_R1.EntityInsentient
import net.minecraft.server.v1_13_R1.PathfinderGoal
import net.minecraft.server.v1_13_R1.PathfinderGoalSelector
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_13_R1.entity.*
import org.bukkit.entity.*
import org.bukkit.util.Vector
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.*

/**
 * Created by ysl3000
 */
class CraftInsentient private constructor(private val handle: EntityInsentient) : Insentient {
    override fun getLookingAt(): Location =
            Location(
                    handle.bukkitEntity.world,
                    handle.controllerLook.e(),
                    handle.controllerLook.f(),
                    handle.controllerLook.g()
            )

    override fun getControllerLook(): ControllerLook = controllerLook
    override fun getControllerMove(): com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerMove = controllerMove
    override fun getNavigation(): Navigation = navigation

    override fun getTargetSelector(): com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector = targetSelector
    override fun getGoalSelector(): com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector = goalSelector

    override fun getControllerJump(): com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerJump = controllerJump

    override fun getHeadHeight(): Float = handle.headHeight

    override fun getDefaultYaw(): Int = handle.L()

    override fun getDefaultPitch(): Int = handle.K()

    override fun getBukkitEntity(): Entity = handle.bukkitEntity


    private val nmsGoals = HashMap<com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal, PathfinderGoal>()
    private val nmsTargetGoals = HashMap<com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal, PathfinderGoal>()

    constructor(flying: Flying) : this((flying as CraftFlying).handle)

    private val navigation: Navigation
    private val controllerLook: ControllerLook
    private val controllerMove: ControllerMove
    private val controllerJump: ControllerJump
    private val targetSelector: GoalSelector
    private val goalSelector: GoalSelector

    init {
        navigation = CraftNavigation(handle.navigation)
        controllerLook = ControllerLookImpl()
        controllerMove = ControllerMove()
        controllerJump = ControllerJump()
        targetSelector = GoalSelector()
        goalSelector = GoalSelector()
    }


    constructor(enderDragon: EnderDragon) : this((enderDragon as CraftEnderDragon).handle)

    constructor(creature: Creature) : this((creature as CraftCreature).handle)

    constructor(mob: Mob) : this((mob as CraftMob).handle)

    constructor(ambient: Ambient) : this((ambient as CraftAmbient).handle)

    constructor(slime: Slime) : this((slime as CraftSlime).handle)

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

    override fun lookAt(location: Location) {
        handle.controllerLook
                .a(location.x, location.y, location.z, location.yaw,
                        location.pitch)
    }

    override fun lookAt(entity: Entity) {
        lookAt(entity.location)
    }

    override fun setMovementDirection(direction: Vector, speed: Double) {
        handle.controllerMove.a(direction.x, direction.blockY.toDouble(), direction.z, speed)
    }

    override fun setStrafeDirection(forward: Float, sideward: Float) {
        handle.controllerMove.a(forward, sideward)
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

    override fun hasPositionChanged(): Boolean {
        return handle.positionChanged
    }

    override fun onEntityKill(livingEntity: LivingEntity) {
        handle.b((livingEntity as CraftLivingEntity).handle)
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
        override fun getX(): Double = handle.controllerMove.c()

        override fun getY(): Double = handle.controllerMove.d()

        override fun getZ(): Double = handle.controllerMove.e()

        override fun getSpeed(): Double = handle.controllerMove.f()

        override fun isOperationMove(): Boolean = handle.controllerMove.b()

        override fun move(motionX: Double, motionY: Double, motionZ: Double, speed: Double) =
                this@CraftInsentient.setMovementDirection(Vector(motionX, motionY, motionZ), speed)

        override fun move(forward: Float, sideward: Float) =
                this@CraftInsentient.setStrafeDirection(forward, sideward)

        override fun update() =
                handle.controllerMove.a()

    }

    private inner class ControllerLookImpl : com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerLook {

        /**
         * Checks if the looking is reset
         *
         * @return if looking is default
         */
        override fun isReset(): Boolean = handle.controllerLook.b()

        /**
         * Gets the X-Location the entity is looking to
         *
         * @return x-location
         */
        override fun getLocationX(): Double = handle.controllerMove.c()

        /**
         * Gets the Y-Location the entity is looking to
         *
         * @return y-location
         */
        override fun getLocationY(): Double = handle.controllerMove.d()

        /**
         * Gets the Z-Location the entity is looking to
         *
         * @return z-location
         */
        override fun getLocationZ(): Double = handle.controllerMove.f()

        /**
         * The entity will look to the given coordinates
         *
         * @param x x-location
         * @param y y-location
         * @param z z-location
         * @param yaw head rotation yaw
         * @param pitch head rotation pitch
         */
        override fun lookAt(x: Double, y: Double, z: Double, yaw: Float, pitch: Float) = this@CraftInsentient
                .lookAt(Location(handle.bukkitEntity.world, x, y, z, yaw, pitch))

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
    }

    companion object {


        private var reset: Method? = null

        init {

            try {
                reset = EntityInsentient::class.java.getDeclaredMethod("n")
                reset!!.isAccessible = true
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            }

        }
    }

}
