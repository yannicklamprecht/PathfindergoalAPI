package com.github.ysl3000.pathfindergoalapi.pathfinding;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

/**
 * Created by Yannick on 30.11.2016.
 */
public interface Navigation {

    /**
     * Will let the entity walk/move to the given location with default speed
     *
     * @param location to walk
     */
    void moveTo(Location location);
    /**
     * Will let the entity walk/move to the given entity's location with default speed
     *
     * @param entity to walk to
     */
    void moveTo(Entity entity);
    /**
     * Will let the entity walk/move to the given location with given speed
     *
     * @param location to walk
     * @param speed    how fast the entity walks
     * @return true if success
     */
    boolean moveTo(Location location, double speed);
    /**
     * Will let the entity walk/move to the given entity's location with given speed
     *
     * @param entity to walk to
     * @param speed  how fast the entity walks
     * @return true if success
     */
    boolean moveTo(Entity entity, double speed);
    /**
     * Will set the entity's walkspeed
     *
     * @param speed walkspeed
     */
    void setSpeed(double speed);
    boolean isMovementSet();
    /**
     * sets active PathEntity to null
     */
    void clearPathEntity();
    /**
     * The maximal distance the pathfinder search
     *
     * @return distance
     */
    float getPathSearchRange();


}