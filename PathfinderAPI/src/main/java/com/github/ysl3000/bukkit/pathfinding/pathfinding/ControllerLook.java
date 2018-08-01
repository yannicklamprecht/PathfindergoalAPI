package com.github.ysl3000.bukkit.pathfinding.pathfinding;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient;

/**
 * @Deprecation {{@link com.github.ysl3000.bukkit.pathfinding.entity.Insentient}} Created by Yannick
 * on 30.11.2016.
 */
@Deprecated
public interface ControllerLook {

  /**
   * * Use
   *
   * @param x x-location
   * @param y y-location
   * @param z z-location
   * @param pitch head rotation pitch
   * @param yaw head rotation yaw
   * @Deprecation {{@link com.github.ysl3000.bukkit.pathfinding.entity.Insentient#lookAt(Location)}}
   *
   * The entity will look to the given coordinates
   */
  @Deprecated
  void lookAt(double x, double y, double z, float yaw, float pitch);

  /**
   * Use
   *
   * @param location the entity should look to
   * @Deprecation {{@link com.github.ysl3000.bukkit.pathfinding.entity.Insentient#lookAt(Location)}}
   *
   * The entity will look to the given location
   */
  @Deprecated
  void lookAt(Location location);

  /**
   * Use
   *
   * @param entity the entity which is targeted with eyes
   * @param pitch head rotation pitch
   * @param yaw head rotation yaw
   * @Deprecation {{@link com.github.ysl3000.bukkit.pathfinding.entity.Insentient#lookAt(Entity)}}
   * The entity will look to the given entity
   */
  @Deprecated
  void lookAt(Entity entity, float yaw, float pitch);

  /**
   * Resets the current looking
   */
  @Deprecated
  void reset();

  /**
   * Checks if the looking is reset
   *
   * @return if looking is default
   */
  @Deprecated
  boolean isReset();

  /**
   * Use
   *
   * @return x-location
   * @Deprecation {{@link Insentient#getLookingAt()}}
   *
   * Gets the X-Location the entity is looking to
   */
  @Deprecated
  double getLocationX();

  /**
   * Use
   *
   * @return y-location
   * @Deprecation {{@link Insentient#getLookingAt()}}
   *
   * Gets the Y-Location the entity is looking to
   */
  @Deprecated
  double getLocationY();

  /**
   * Use
   *
   * @return z-location
   * @Deprecation {{@link Insentient#getLookingAt()}}
   *
   * Gets the Z-Location the entity is looking to
   */
  @Deprecated
  double getLocationZ();
}