package com.github.ysl3000.bukkit.pathfinding.goals

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal
import org.bukkit.Material
import org.bukkit.entity.Creature
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * Created by 2008Choco
 */
class PathfinderGoalGimmiCookie(private val pathfinderGoalEntity: Insentient, private val creature: Creature) : PathfinderGoal {
    private var isGenerous = true
    private var hasGivenCookie = false
    private var nearbyEntities: List<Entity>? = null
    private var nearestPlayer: Player? = null

    override fun shouldExecute(): Boolean {
        if (!isGenerous) {
            return false
        }

        val nearbyEntities = creature.getNearbyEntities(5.0, 5.0, 5.0)
        if (nearbyEntities.isEmpty()) {
            return false
        }

        this.nearbyEntities = nearbyEntities
        return nearbyEntities.stream().anyMatch { Player::class.java.isInstance(it) }
    }

    override fun shouldTerminate(): Boolean {
        return !hasGivenCookie
    }

    override fun init() {
        this.nearestPlayer = getNearestPlayerFrom(nearbyEntities!!).orElse(null)
        this.nearbyEntities = null
        this.creature.equipment?.setItemInMainHand(COOKIE)
        this.creature.target = nearestPlayer
        this.pathfinderGoalEntity.getNavigation().moveTo(nearestPlayer!!)


    }

    override fun execute() {
        this.pathfinderGoalEntity.getControllerJump().jump()
        if (creature.location.distanceSquared(nearestPlayer!!.location) <= 1) {
            this.creature.world.dropItem(nearestPlayer!!.location, COOKIE)
            this.creature.equipment?.setItemInMainHand(null)
            this.isGenerous = false
            this.hasGivenCookie = true
        }
    }

    override fun reset() {
        this.nearestPlayer = null
        this.hasGivenCookie = false
    }

    private fun getNearestPlayerFrom(entities: List<Entity>): Optional<Player> {
        return entities.stream()
                .filter { Player::class.java.isInstance(it) }
                .map<Player> { Player::class.java.cast(it) }
                .min(Comparator
                        .comparingDouble { p -> creature.location.distanceSquared(p.location) })
    }

    companion object {

        private val COOKIE = ItemStack(Material.COOKIE)
    }

}