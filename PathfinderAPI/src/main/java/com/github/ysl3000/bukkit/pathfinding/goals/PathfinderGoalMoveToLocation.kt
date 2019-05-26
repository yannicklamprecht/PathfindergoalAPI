package com.github.ysl3000.bukkit.pathfinding.goals

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient
import com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal
import org.bukkit.Location
import org.bukkit.Material

class PathfinderGoalMoveToLocation(private val pathfinderGoalEntity: Insentient, private val targetLocation: Location,
                                   private val walkSpeed: Double, private val distance: Double) : PathfinderGoal {
    private val navigation: Navigation = pathfinderGoalEntity.getNavigation()

    private var isAlreadySet = false
    private val isDone = false


    override fun shouldExecute(): Boolean {
        return if (this.isAlreadySet) {
            false
        } else pathfinderGoalEntity.getBukkitEntity().location.distanceSquared(targetLocation) > distance
    }


    override fun shouldTerminate(): Boolean {
        isAlreadySet = !pathfinderGoalEntity.getNavigation().isDoneNavigating()
        return isAlreadySet
    }

    override fun init() {
        if (!this.isAlreadySet) {
            this.navigation.moveTo(this.targetLocation, walkSpeed)
        }
    }

    override fun execute() {
        if (pathfinderGoalEntity.getBukkitEntity().location.add(pathfinderGoalEntity.getBukkitEntity().location.direction.normalize())
                        .block.type != Material.AIR) {
            pathfinderGoalEntity.getControllerJump().jump()
        }

    }

    override fun reset() {}


    private fun setMessage(message: String) {
        pathfinderGoalEntity.getBukkitEntity().customName = message
        pathfinderGoalEntity.getBukkitEntity().isCustomNameVisible = true
    }

}