package com.github.ysl3000.bukkit.pathfinding.pathfinding;

import org.bukkit.entity.Ambient;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Flying;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient;

/**
 * Created by ysl3000 on 08.12.16.
 */
public interface PathfinderManager {

  /**
   * Returns a pathfinderGoalEntity from creature
   *
   * @param creature entity you want to get the PathfinderGoalEntity
   * @return pathfinderGoalEntity the entity you can apply pathfindergoals on to
   */
  Insentient getPathfindeGoalEntity(Creature creature);

  /**
   * Returns a pathfinderGoalEntity from flying
   *
   * @param flying entity you want to get the PathfinderGoalEntity
   * @return pathfinderGoalEntity the entity you can apply pathfindergoals on to
   */
  Insentient getPathfinderGoalEntity(Flying flying);

  /**
   * Returns a pathfinderGoalEntity from ambient
   *
   * @param ambient entity you want to get the PathfinderGoalEntity
   * @return pathfinderGoalEntity the entity you can apply pathfindergoals on to
   */
  Insentient getPathfinderGoalEntity(Ambient ambient);

  /**
   * Returns a pathfinderGoalEntity from slime
   *
   * @param slime entity you want to get the PathfinderGoalEntity
   * @return pathfinderGoalEntity the entity you can apply pathfindergoals on to
   */
  Insentient getPathfinderGoalEntity(Slime slime);


  /**
   * Returns a pathfinderGoalEntity from slime
   *
   * @param enderDragon entity you want to get the PathfinderGoalEntity
   * @return pathfinderGoalEntity the entity you can apply pathfindergoals on to
   */
  Insentient getPathfinderGoalEntity(EnderDragon enderDragon);


  /**
   * Returns a instance of the pathfinderPlayer
   *
   * @param player the Bukkit Player
   * @return pathfinderPlayer
   */
  PathfinderPlayer getPathfinderPlayer(Player player);

}
