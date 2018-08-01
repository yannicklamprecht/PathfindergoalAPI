package com.github.ysl3000.bukkit.pathfinding.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerJump;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerLook;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerMove;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector;

/**
 * Created by Yannick on 30.11.2016.
 */
public interface Insentient {


  void addPathfinderGoal(int priority, PathfinderGoal pathfinderGoal);

  void removePathfinderGoal(PathfinderGoal pathfinderGoal);

  boolean hasPathfinderGoal(PathfinderGoal pathfinderGoal);

  void clearPathfinderGoals();

  void addTargetPathfinderGoal(int priority, PathfinderGoal pathfinderGoal);

  void removeTargetPathfinderGoal(PathfinderGoal pathfinderGoal);

  boolean hasTargetPathfinderGoal(PathfinderGoal pathfinderGoal);

  void clearTargetPathfinderGoals();


  void jump();


  /**
   * The entity will look to the given location
   *
   * @param location the entity should look to
   */
  void lookAt(Location location);

  /**
   * The entity will look to the given entity
   *
   * @param entity the entity which is targeted with eyes
   */
  void lookAt(Entity entity);

  Location getLookingAt();


  void setMovementDirection(Vector direction, double speed);

  void setStrafeDirection(float forward, float sideward);


  /**
   * Will return the PathfinderGoalTargetSelector
   *
   * @return targetSelector
   */
  @Deprecated
  PathfinderGoalSelector getTargetSelector();

  /**
   * Will return the PathfinderGoalSelector
   *
   * @return selector
   */
  @Deprecated
  PathfinderGoalSelector getGoalSelector();

  /**
   * Will reset goals to default one
   */
  void resetGoalsToDefault();

  /**
   * Get the ControllerJump for jumping
   *
   * @return controllerJump to let the entity jump
   */
  ControllerJump getControllerJump();

  /**
   * Get the ControllerLook for looking
   *
   * @return controllerLook to let the entity look at a target
   */
  ControllerLook getControllerLook();


  /**
   * Get the controllerMove direct movement
   *
   * @return controllerMove to move the entity directly
   */
  ControllerMove getControllerMove();

  /**
   * Get the Navigation for moving Entity
   *
   * @return navigation to control movements
   */
  Navigation getNavigation();


  /**
   * Get the entities headHeight
   *
   * @return headHeight
   */
  float getHeadHeight();


  /**
   * Will return the entities default yaw
   *
   * @return yaw
   */
  int getDefaultYaw();

  /**
   * Will return the entities default pitch
   *
   * @return pitch
   */
  int getDefaultPitch();


  /**
   * Will return if entity changed position
   *
   * @return true if positionchange happened after last call
   */
  boolean hasPositionChanged();

  /**
   * method gets called when the entity kills another entity
   *
   * @param livingEntity the other entity
   */
  void onEntityKill(LivingEntity livingEntity);

  /**
   * Will return the LivingEntity of Bukkit
   *
   * @return entity
   */
  Entity getBukkitEntity();


  /**
   * @param motionX relative motionX
   * @param motionY relative motionY
   * @param motionZ relative motionZ
   * @deprecated use {@link Insentient#setMovementDirection(Vector, double)} Will move the Entity
   * relative
   */
  @Deprecated
  void moveRelative(double motionX, double motionY, double motionZ);


  /**
   * @param motionX relative motionX
   * @param motionY relative motionY
   * @param motionZ relative motionZ
   * @param speed the speed multiplier
   * @deprecated use {@link Insentient#setMovementDirection(Vector, double)} Will move the Entity
   * relative
   */
  @Deprecated
  void moveRelative(double motionX, double motionY, double motionZ, double speed);


  /**
   * Rotates the entity
   *
   * @param yaw absolute yaw
   * @param pitch absolute pitch
   */
  void setRotation(float yaw, float pitch);


  /**
   * Updates the the angles for rendering
   */
  void updateRenderAngles();

}
