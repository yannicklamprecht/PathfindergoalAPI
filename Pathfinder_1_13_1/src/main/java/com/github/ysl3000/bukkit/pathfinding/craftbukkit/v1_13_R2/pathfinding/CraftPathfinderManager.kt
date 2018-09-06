package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R2.pathfinding

import org.bukkit.entity.Ambient
import org.bukkit.entity.Creature
import org.bukkit.entity.EnderDragon
import org.bukkit.entity.Flying
import org.bukkit.entity.Player
import org.bukkit.entity.Slime

import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R2.entity.CraftInsentient
import com.github.ysl3000.bukkit.pathfinding.entity.Insentient
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderManager
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderPlayer

/**
 * Created by ysl3000
 */
class CraftPathfinderManager : PathfinderManager {

    override fun getPathfindeGoalEntity(creature: Creature): Insentient {
        return CraftInsentient(creature)
    }

    override fun getPathfinderGoalEntity(flying: Flying): Insentient {
        return CraftInsentient(flying)
    }

    override fun getPathfinderGoalEntity(ambient: Ambient): Insentient {
        return CraftInsentient(ambient)
    }

    override fun getPathfinderGoalEntity(slime: Slime): Insentient {
        return CraftInsentient(slime)
    }

    override fun getPathfinderGoalEntity(enderDragon: EnderDragon): Insentient {
        return CraftInsentient(enderDragon)
    }

    override fun getPathfinderPlayer(player: Player): PathfinderPlayer {
        return CraftPathfinderPlayer(player)
    }
}
