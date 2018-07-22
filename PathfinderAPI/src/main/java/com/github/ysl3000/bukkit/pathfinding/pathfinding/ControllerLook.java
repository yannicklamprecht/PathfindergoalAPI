package com.github.ysl3000.bukkit.pathfinding.pathfinding;

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

/**
 * @Deprecation {{@link com.github.ysl3000.bukkit.pathfinding.entity.Insentient}}
 * Created by Yannick on 30.11.2016.
 */
@Deprecated
public interface ControllerLook {
    /**
     * * Use
     * @Deprecation {{@link com.github.ysl3000.bukkit.pathfinding.entity.Insentient#lookAt(Location)}}
     *
     * The entity will look to the given coordinates
     * @param x x-location
     * @param y y-location
     * @param z z-location
     * @param pitch head rotation pitch
     * @param yaw head rotation yaw
     */
    @Deprecated
    void lookAt(double x, double y, double z, float yaw, float pitch);

    /**
     * Use
     * @Deprecation {{@link com.github.ysl3000.bukkit.pathfinding.entity.Insentient#lookAt(Location)}}
     *
     * The entity will look to the given location
     *
     * @param location the entity should look to
     */
    @Deprecated
    void lookAt(Location location);

    /**
     * Use
     * @Deprecation {{@link com.github.ysl3000.bukkit.pathfinding.entity.Insentient#lookAt(Entity)}}
     * The entity will look to the given entity
     * @param entity the entity which is targeted with eyes
     * @param pitch head rotation pitch
     * @param yaw head rotation yaw
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
     * @return if looking is default
     */
    @Deprecated
    boolean isReset();

    /**
     * Use
     * @Deprecation {{@link Insentient#getLookingAt()}}
     *
     * Gets the X-Location the entity is looking to
     *
     * @return x-location
     */
    @Deprecated
    double getLocationX();
    /**
     * Use
     * @Deprecation {{@link Insentient#getLookingAt()}}
     *
     * Gets the Y-Location the entity is looking to
     *
     * @return y-location
     */
    @Deprecated
    double getLocationY();
    /**
     * Use
     * @Deprecation {{@link Insentient#getLookingAt()}}
     *
     * Gets the Z-Location the entity is looking to
     *
     * @return z-location
     */
    @Deprecated
    double getLocationZ();
}