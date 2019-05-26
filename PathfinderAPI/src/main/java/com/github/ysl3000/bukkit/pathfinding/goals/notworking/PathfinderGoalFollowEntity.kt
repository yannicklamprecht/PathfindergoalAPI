package com.github.ysl3000.bukkit.pathfinding.goals.notworking

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal
import org.bukkit.Material
import org.bukkit.entity.LivingEntity

class PathfinderGoalFollowEntity(private val pathfinderGoalEntity: Insentient, private val entity: LivingEntity,
                                 private val moveRadius: Double, private val walkspeed: Double) : PathfinderGoal {

    override fun shouldExecute(): Boolean {
        return this.pathfinderGoalEntity.getBukkitEntity().location
                .distanceSquared(entity.location) > moveRadius
    }

    /**
     * Whether the goal should Terminate
     *
     * @return true if should terminate
     */
    override fun shouldTerminate(): Boolean {
        return pathfinderGoalEntity.getNavigation().isDoneNavigating() || this.entity.isDead
    }

    /**
     * Runs initially and should be used to setUp goalEnvironment Condition needs to be defined thus
     * your code in it isn't called
     */
    override fun init() {

    }

    /**
     * Is called when [.shouldExecute] returns true
     */
    override fun execute() {

        if (pathfinderGoalEntity.getBukkitEntity().location
                        .add(pathfinderGoalEntity.getBukkitEntity().location.direction.normalize())
                        .block.type != Material.AIR) {
            this.pathfinderGoalEntity.getControllerJump().jump()
        }
    }

    override fun reset() {
        this.pathfinderGoalEntity.getNavigation().moveTo(this.entity, walkspeed)
    }

}