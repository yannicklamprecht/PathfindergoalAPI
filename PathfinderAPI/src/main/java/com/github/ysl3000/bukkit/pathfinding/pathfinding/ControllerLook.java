package com.github.ysl3000.bukkit.pathfinding.pathfinding;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

/**
 * Created by Yannick on 30.11.2016.
 */
public interface ControllerLook {
    /**
     * The entity will look to the given coordinates
     * @param x x-location
     * @param y y-location
     * @param z z-location
     * @param pitch head rotation pitch
     * @param yaw head rotation yaw
     */
    void lookAt(double x, double y, double z, float yaw, float pitch);

    /**
     * The entity will look to the given location
     *
     * @param location the entity should look to
     */
    void lookAt(Location location);

    /**
     * The entity will look to the given entity
     * @param entity the entity which is targeted with eyes
     * @param pitch head rotation pitch
     * @param yaw head rotation yaw
     */
    void lookAt(Entity entity, float yaw, float pitch);

    /**
     * Resets the current looking
     */
    void reset();

    /**
     * Checks if the looking is reset
     * @return if looking is default
     */
    boolean isReset();

    /**
     * Gets the X-Location the entity is looking to
     *
     * @return x-location
     */
    double getLocationX();
    /**
     * Gets the Y-Location the entity is looking to
     *
     * @return y-location
     */
    double getLocationY();
    /**
     * Gets the Z-Location the entity is looking to
     *
     * @return z-location
     */
    double getLocationZ();
}