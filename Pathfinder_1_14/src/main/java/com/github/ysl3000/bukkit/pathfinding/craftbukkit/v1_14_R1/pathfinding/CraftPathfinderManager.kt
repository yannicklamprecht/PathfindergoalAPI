package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_14_R1.pathfinding

import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_14_R1.entity.CraftInsentient
import com.github.ysl3000.bukkit.pathfinding.entity.Insentient
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderManager
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderPlayer
import org.bukkit.entity.Ambient
import org.bukkit.entity.Creature
import org.bukkit.entity.EnderDragon
import org.bukkit.entity.Flying
import org.bukkit.entity.Player
import org.bukkit.entity.Slime

class CraftPathfinderManager : PathfinderManager {

    override fun getPathfindeGoalEntity(creature: Creature): Insentient = CraftInsentient(creature)

    override fun getPathfinderGoalEntity(flying: Flying): Insentient = CraftInsentient(flying)

    override fun getPathfinderGoalEntity(ambient: Ambient): Insentient = CraftInsentient(ambient)

    override fun getPathfinderGoalEntity(slime: Slime): Insentient = CraftInsentient(slime)

    override fun getPathfinderGoalEntity(enderDragon: EnderDragon): Insentient = CraftInsentient(enderDragon)

    override fun getPathfinderPlayer(player: Player): PathfinderPlayer = CraftPathfinderPlayer(player)
}
