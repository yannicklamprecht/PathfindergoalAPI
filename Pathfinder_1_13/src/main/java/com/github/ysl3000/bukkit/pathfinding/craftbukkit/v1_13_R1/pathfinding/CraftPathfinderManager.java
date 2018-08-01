package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.pathfinding;

import org.bukkit.entity.Ambient;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Flying;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;

import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.entity.CraftInsentient;
import com.github.ysl3000.bukkit.pathfinding.entity.Insentient;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderManager;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderPlayer;

/**
 * Created by ysl3000
 */
public class CraftPathfinderManager implements PathfinderManager {

  @Override
  public Insentient getPathfindeGoalEntity(Creature creature) {
    return new CraftInsentient(creature);
  }

  @Override
  public Insentient getPathfinderGoalEntity(Flying flying) {
    return new CraftInsentient(flying);
  }

  @Override
  public Insentient getPathfinderGoalEntity(Ambient ambient) {
    return new CraftInsentient(ambient);
  }

  @Override
  public Insentient getPathfinderGoalEntity(Slime slime) {
    return new CraftInsentient(slime);
  }

  @Override
  public Insentient getPathfinderGoalEntity(EnderDragon enderDragon) {
    return new CraftInsentient(enderDragon);
  }

  @Override
  public PathfinderPlayer getPathfinderPlayer(Player player) {
    return new CraftPathfinderPlayer(player);
  }
}
