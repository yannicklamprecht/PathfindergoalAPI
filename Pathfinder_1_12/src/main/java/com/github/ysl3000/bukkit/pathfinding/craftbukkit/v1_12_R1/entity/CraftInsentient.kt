package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_12_R1.entity

import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_12_R1.pathfinding.CraftNavigation
import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_12_R1.pathfinding.CraftPathfinderGoalWrapper
import com.github.ysl3000.bukkit.pathfinding.entity.Insentient
import net.minecraft.server.v1_12_R1.EntityInsentient
import net.minecraft.server.v1_12_R1.PathfinderGoal
import net.minecraft.server.v1_12_R1.PathfinderGoalSelector
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_12_R1.entity.*
import org.bukkit.entity.*
import org.bukkit.util.Vector
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * Created by ysl3000
 */
class CraftInsentient private constructor(private val handle: EntityInsentient) : Insentient {

    override fun getNavigation(): com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation = CraftNavigation(handle.navigation)

    override fun getLookingAt(): Location = Location(handle.bukkitEntity.world, handle.controllerLook.e(), handle.controllerLook.f(),
            handle.controllerLook.g())

    override fun getTargetSelector(): com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector = TargetGoalSelector()

    override fun getGoalSelector(): com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector = GoalSelector()

    override fun getControllerJump(): com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerJump = ControllerJump()

    override fun getControllerLook(): com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerLook = ControllerLookImpl()

    override fun getControllerMove(): com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerMove = ControllerMove()

    override fun getHeadHeight(): Float = handle.headHeight

    override fun getDefaultYaw(): Int = handle.O()

    override fun getDefaultPitch(): Int = handle.N()

    override fun getBukkitEntity(): Entity = handle.bukkitEntity

    constructor(flying: Flying) : this((flying as CraftFlying).handle)

    constructor(enderDragon: EnderDragon) : this((enderDragon as CraftEnderDragon).handle)

    constructor(creature: Creature) : this((creature as CraftCreature).handle)

    constructor(ambient: Ambient) : this((ambient as CraftAmbient).handle)

    constructor(slime: Slime) : this((slime as CraftSlime).handle)

    private val nmsGoals: MutableMap<com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal, PathfinderGoal> = HashMap()
    private val nmsTargetGoals: MutableMap<com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal, PathfinderGoal> = HashMap()

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

        override fun isOperationMove(): Boolean = handle.controllerMove.h == net.minecraft.server.v1_12_R1.ControllerMove.Operation.MOVE_TO

        override fun getX(): Double = handle.controllerMove.c()

        override fun getY(): Double = handle.controllerMove.d()

        override fun getZ(): Double = handle.controllerMove.e()

        override fun getSpeed(): Double = handle.controllerMove.f()

        override fun move(motionX: Double, motionY: Double, motionZ: Double, speed: Double) =
                this@CraftInsentient.setMovementDirection(Vector(motionX, motionY, motionZ), speed)

        override fun move(forward: Float, sideward: Float) =
                this@CraftInsentient.setStrafeDirection(forward, sideward)

        override fun update() = handle.controllerMove.a()
    }

    private inner class ControllerLookImpl : com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerLook {

        override fun isReset(): Boolean = handle.controllerLook.b()

        override fun getLocationX(): Double = handle.controllerMove.d()

        override fun getLocationY(): Double = handle.controllerMove.e()

        override fun getLocationZ(): Double = handle.controllerMove.f()

        override fun lookAt(x: Double, y: Double, z: Double, yaw: Float, pitch: Float) = this@CraftInsentient
                .lookAt(Location(handle.bukkitEntity.world, x, y, z, yaw, pitch))

        override fun lookAt(location: Location) = this@CraftInsentient.lookAt(location)

        override fun lookAt(entity: Entity, yaw: Float, pitch: Float) = this@CraftInsentient.lookAt(entity)

        override fun reset() = handle.controllerLook.a()
    }

    companion object {
        private var reset: Method? = null

        init {

            try {
                reset = EntityInsentient::class.java.getDeclaredMethod("r")
                reset!!.isAccessible = true
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            }
        }
    }
}