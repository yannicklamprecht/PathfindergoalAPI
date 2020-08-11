package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_16_R2.entity

import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_16_R2.pathfinding.CraftNavigation
import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_16_R2.pathfinding.CraftPathfinderGoalWrapper
import com.github.ysl3000.bukkit.pathfinding.entity.Insentient
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal
import net.minecraft.server.v1_16_R2.EntityInsentient
import net.minecraft.server.v1_16_R2.PathfinderGoalSelector
import org.bukkit.Location
import org.bukkit.attribute.Attributable
import org.bukkit.attribute.Attribute
import org.bukkit.craftbukkit.v1_16_R2.entity.*
import org.bukkit.entity.*
import org.bukkit.util.Vector
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.*

class CraftInsentient private constructor(private val handle: EntityInsentient) : Insentient {


    private val nmsGoals = HashMap<PathfinderGoal, net.minecraft.server.v1_16_R2.PathfinderGoal>()
    private val nmsTargetGoals = HashMap<PathfinderGoal, net.minecraft.server.v1_16_R2.PathfinderGoal>()

    private val navigation: com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation

    constructor(flying: Flying) : this((flying as CraftFlying).handle)

    init {
        this.navigation = CraftNavigation(handle.navigation, handle, (handle.bukkitEntity as Attributable).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.value
                ?: 0.7)
    }


    constructor(enderDragon: EnderDragon) : this((enderDragon as CraftEnderDragon).handle)

    constructor(creature: Creature) : this((creature as CraftCreature).handle)

    constructor(mob: Mob) : this((mob as CraftMob).handle)

    constructor(ambient: Ambient) : this((ambient as CraftAmbient).handle)

    constructor(slime: Slime) : this((slime as CraftSlime).handle)

    override fun addPathfinderGoal(priority: Int,
                                   pathfinderGoal: PathfinderGoal) {
        val goalWrapper = CraftPathfinderGoalWrapper(pathfinderGoal)
        this.nmsGoals[pathfinderGoal] = goalWrapper
        handle.goalSelector.a(priority, goalWrapper)
    }

    override fun removePathfinderGoal(
            pathfinderGoal: PathfinderGoal) {
        if (nmsGoals.containsKey(pathfinderGoal)) {
            val nmsGoal = nmsGoals.remove(pathfinderGoal)
            handle.goalSelector.a(nmsGoal)
        }
    }

    override fun hasPathfinderGoal(
            pathfinderGoal: PathfinderGoal): Boolean = nmsGoals.containsKey(pathfinderGoal)

    override fun clearPathfinderGoals() {
        handle.goalSelector = PathfinderGoalSelector(handle.getWorld().methodProfilerSupplier)
        nmsGoals.clear()
    }


    override fun addTargetPathfinderGoal(priority: Int,
                                         pathfinderGoal: PathfinderGoal) {
        val goalWrapper = CraftPathfinderGoalWrapper(pathfinderGoal)
        this.nmsTargetGoals[pathfinderGoal] = goalWrapper
        handle.targetSelector.a(priority, goalWrapper)
    }

    override fun removeTargetPathfinderGoal(
            pathfinderGoal: PathfinderGoal) {
        if (nmsTargetGoals.containsKey(pathfinderGoal)) {
            val nmsGoal = nmsTargetGoals.remove(pathfinderGoal)
            handle.goalSelector.a(nmsGoal)
        }
    }

    override fun hasTargetPathfinderGoal(
            pathfinderGoal: PathfinderGoal): Boolean = nmsTargetGoals.containsKey(pathfinderGoal)

    override fun clearTargetPathfinderGoals() {
        handle.targetSelector = PathfinderGoalSelector(handle.getWorld().methodProfilerSupplier)
        nmsTargetGoals.clear()
    }

    override fun jump() {
        handle.controllerJump.jump()
    }

    override fun lookAt(location: Location) = handle.controllerLook
            .a(location.x, location.y, location.z, location.yaw,
                    location.pitch)

    override fun lookAt(entity: Entity) = lookAt(entity.location)

    override fun getLookingAt(): Location = Location(handle.bukkitEntity.world, handle.controllerLook.d(), handle.controllerLook.e(),
            handle.controllerLook.f())

    override fun setMovementDirection(direction: Vector, speed: Double) = handle.controllerMove.a(direction.x, direction.blockY.toDouble(), direction.z, speed)

    override fun setStrafeDirection(forward: Float, sideward: Float) = handle.controllerMove.a(forward, sideward)

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

    override fun getNavigation(): com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation = navigation

    override fun getHeadHeight(): Float = handle.headHeight


    override fun hasPositionChanged(): Boolean = handle.positionChanged

    override fun onEntityKill(livingEntity: LivingEntity) {
        val nms = (livingEntity as CraftLivingEntity).handle
        handle.a(nms.world.server.server.getWorldServer(nms.world.dimensionKey), nms)
    }

    override fun getBukkitEntity(): Entity = handle.bukkitEntity

    override fun setRotation(yaw: Float, pitch: Float) {
        this.handle.yaw = yaw
        this.handle.pitch = pitch
    }

    override fun updateRenderAngles() = handle.controllerMove.a()


    companion object {
        private var reset: Method? = null

        init {

            try {
                reset = EntityInsentient::class.java.getDeclaredMethod("initPathfinder")
                reset!!.isAccessible = true
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            }

        }
    }

}