package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_14_R1.pathfinding

import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_14_R1.entity.CraftInsentient
import com.github.ysl3000.bukkit.pathfinding.entity.Insentient
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderManager
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderPlayer
import org.bukkit.entity.*

class CraftPathfinderManager : PathfinderManager {

    override fun getPathfinderGoalEntity(creature: Creature): Insentient = CraftInsentient(creature)

    override fun getPathfinderGoalEntity(mob: Mob): Insentient = CraftInsentient(mob)

    override fun getPathfindeGoalEntity(creature: Creature): Insentient = CraftInsentient(creature)

    override fun getPathfinderGoalEntity(flying: Flying): Insentient = CraftInsentient(flying)

    override fun getPathfinderGoalEntity(ambient: Ambient): Insentient = CraftInsentient(ambient)

    override fun getPathfinderGoalEntity(slime: Slime): Insentient = CraftInsentient(slime)

    override fun getPathfinderGoalEntity(enderDragon: EnderDragon): Insentient = CraftInsentient(enderDragon)

    override fun getPathfinderPlayer(player: Player): PathfinderPlayer = CraftPathfinderPlayer(player)
}
