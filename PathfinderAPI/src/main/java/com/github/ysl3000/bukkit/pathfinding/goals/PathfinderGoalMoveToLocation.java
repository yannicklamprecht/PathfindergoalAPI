package com.github.ysl3000.bukkit.pathfinding.goals;

import org.bukkit.Location;
import org.bukkit.Material;

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal;

public class PathfinderGoalMoveToLocation implements PathfinderGoal {


  private final Location targetLocation;
  private final Navigation navigation;
  private Insentient pathfinderGoalEntity;
  private double walkSpeed;
  private double distance;

  private boolean isAlreadySet = false;
  private boolean isDone = false;


  public PathfinderGoalMoveToLocation(Insentient pathfinderGoalEntity, Location targetLocation,
      double walkSpeed, double distance) {
    this.targetLocation = targetLocation;
    this.pathfinderGoalEntity = pathfinderGoalEntity;
    this.navigation = pathfinderGoalEntity.getNavigation();
    this.walkSpeed = walkSpeed;
    this.distance = distance;
  }

  public boolean shouldExecute() {
    if (this.isAlreadySet) {
      return false;
    }
    return pathfinderGoalEntity.getBukkitEntity().getLocation().distanceSquared(targetLocation)
        > distance;
  }


  @Override
  public boolean shouldTerminate() {
    return this.isAlreadySet = !this.pathfinderGoalEntity.getNavigation().isDoneNavigating();
  }

  @Override
  public void init() {
    if (!this.isAlreadySet) {
      this.navigation.moveTo(this.targetLocation, walkSpeed);
    }
  }

  @Override
  public void execute() {
    if (pathfinderGoalEntity.getBukkitEntity().getLocation().
        add(pathfinderGoalEntity.getBukkitEntity().getLocation().getDirection().normalize())
        .getBlock().getType() != Material.AIR) {
      pathfinderGoalEntity.getControllerJump().jump();
    }

  }

  @Override
  public void reset() {
  }


  private void setMessage(String message) {
    pathfinderGoalEntity.getBukkitEntity().setCustomName(message);
    pathfinderGoalEntity.getBukkitEntity().setCustomNameVisible(true);
  }

}