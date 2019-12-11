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

    override fun getHeadHeight(): Float = handle.headHeight

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

    override fun setRotation(yaw: Float, pitch: Float) {
        this.handle.yaw = yaw
        this.handle.pitch = pitch
    }

    override fun updateRenderAngles() {
        handle.controllerMove.a()
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