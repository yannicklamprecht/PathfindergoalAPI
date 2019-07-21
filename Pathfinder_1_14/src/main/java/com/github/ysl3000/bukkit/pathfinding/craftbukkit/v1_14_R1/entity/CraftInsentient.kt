package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_14_R1.entity


import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_14_R1.pathfinding.CraftNavigation
import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_14_R1.pathfinding.CraftPathfinderGoalWrapper
import com.github.ysl3000.bukkit.pathfinding.entity.Insentient
import net.minecraft.server.v1_14_R1.EntityInsentient
import net.minecraft.server.v1_14_R1.PathfinderGoal
import net.minecraft.server.v1_14_R1.PathfinderGoalSelector
import org.bukkit.Location
import org.bukkit.attribute.Attributable
import org.bukkit.attribute.Attribute
import org.bukkit.craftbukkit.v1_14_R1.entity.*
import org.bukkit.entity.*
import org.bukkit.util.Vector
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.*

class CraftInsentient private constructor(private val handle: EntityInsentient) : Insentient {


    private val nmsGoals = HashMap<com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal, PathfinderGoal>()
    private val nmsTargetGoals = HashMap<com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal, PathfinderGoal>()

    private val navigation: com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation

    constructor(flying: Flying) : this((flying as CraftFlying).handle)

    init {
        this.navigation = CraftNavigation(handle.navigation,(handle.bukkitEntity as Attributable).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.value?:0.7)
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
            pathfinderGoal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal): Boolean = nmsGoals.containsKey(pathfinderGoal)

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
            pathfinderGoal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal): Boolean = nmsTargetGoals.containsKey(pathfinderGoal)

    override fun clearTargetPathfinderGoals() {
        handle.targetSelector = PathfinderGoalSelector(handle.getWorld().methodProfiler)
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

    override fun getTargetSelector(): com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector = TargetGoalSelector()

    override fun getGoalSelector(): com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector = GoalSelector()

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

    override fun getControllerJump(): com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerJump = ControllerJump()

    override fun getControllerLook(): com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerLook = ControllerLookImpl()

    override fun getControllerMove(): com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerMove = ControllerMove()

    override fun getNavigation(): com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation = navigation

    override fun getHeadHeight(): Float = handle.headHeight

    override fun getDefaultYaw(): Int = handle.dC()

    override fun getDefaultPitch(): Int = handle.M()

    override fun hasPositionChanged(): Boolean = handle.positionChanged

    override fun onEntityKill(livingEntity: LivingEntity) = handle.b((livingEntity as CraftLivingEntity).handle)

    override fun getBukkitEntity(): Entity = handle.bukkitEntity

    override fun moveRelative(motionX: Double, motionY: Double, motionZ: Double) = moveRelative(motionX, motionY, motionZ, 1.0)

    override fun moveRelative(motionX: Double, motionY: Double, motionZ: Double, speed: Double) {
        this.handle.mot = this.handle.mot.add(motionX * speed, motionY * speed, motionZ * speed)
        this.handle.recalcPosition()
    }

    override fun setRotation(yaw: Float, pitch: Float) {
        this.handle.yaw = yaw
        this.handle.pitch = pitch
    }

    override fun updateRenderAngles() = handle.controllerMove.a()


    private inner class GoalSelector : com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector {

        override fun addPathfinderGoal(priority: Int,
                                       goal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal) = this@CraftInsentient.addPathfinderGoal(priority, goal)

        override fun removePathfinderGoal(
                goal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal) = this@CraftInsentient.removePathfinderGoal(goal)

        override fun clearGoals() = this@CraftInsentient.clearPathfinderGoals()
    }

    private inner class TargetGoalSelector : com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector {

        override fun addPathfinderGoal(priority: Int,
                                       goal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal) = this@CraftInsentient.addTargetPathfinderGoal(priority, goal)

        override fun removePathfinderGoal(
                goal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal) = this@CraftInsentient.removeTargetPathfinderGoal(goal)


        override fun clearGoals() = this@CraftInsentient.clearTargetPathfinderGoals()
    }

    private inner class ControllerJump : com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerJump {
        override fun jump() = this@CraftInsentient.jump()
    }

    private inner class ControllerMove : com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerMove {

        override fun isOperationMove(): Boolean = handle.controllerMove.b()

        override fun move(motionX: Double, motionY: Double, motionZ: Double, speed: Double) = this@CraftInsentient.setMovementDirection(Vector(motionX, motionY, motionZ), speed)

        override fun move(forward: Float, sideward: Float) = this@CraftInsentient.setStrafeDirection(forward, sideward)

        override fun update() = handle.controllerMove.a()

        override fun getX(): Double = handle.controllerMove.c()

        override fun getY(): Double = handle.controllerMove.d()

        override fun getZ(): Double = handle.controllerMove.e()

        override fun getSpeed(): Double = handle.controllerMove.f()
    }

    private inner class ControllerLookImpl : com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerLook {

        override fun lookAt(x: Double, y: Double, z: Double, yaw: Float, pitch: Float) = this@CraftInsentient.lookAt(Location(handle.bukkitEntity.world, x, y, z, yaw, pitch))

        override fun lookAt(location: Location) = this@CraftInsentient.lookAt(location)

        override fun lookAt(entity: Entity, yaw: Float, pitch: Float) = this@CraftInsentient.lookAt(entity)

        override fun reset() = handle.controllerLook.a()

        override fun isReset(): Boolean = handle.controllerLook.c()

        override fun getLocationX(): Double = handle.controllerMove.d()

        override fun getLocationY(): Double = handle.controllerMove.e()

        override fun getLocationZ(): Double = handle.controllerMove.f()
    }

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
