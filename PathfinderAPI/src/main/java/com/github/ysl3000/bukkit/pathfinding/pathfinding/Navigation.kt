package com.github.ysl3000.bukkit.pathfinding.pathfinding

import org.bukkit.Location
import org.bukkit.entity.Entity

/**
 * Created by Yannick on 30.11.2016.
 */
interface Navigation {

    fun isDoneNavigating(): Boolean

    /**
     * The maximal distance the pathfinder search
     *
     * @return distance
     */
    fun getPathSearchRange(): Float

    /**
     * Will let the entity walk/move to the given location with default speed
     *
     * @param location to walk
     */
    fun moveTo(location: Location)

    /**
     * Will let the entity walk/move to the given entity's location with default speed
     *
     * @param entity to walk to
     */
    fun moveTo(entity: Entity)

    /**
     * Will let the entity walk/move to the given location with given speed
     *
     * @param location to walk
     * @param speed how fast the entity walks
     * @return true if success
     */
    fun moveTo(location: Location, speed: Double): Boolean

    /**
     * Will let the entity walk/move to the given entity's location with given speed
     *
     * @param entity to walk to
     * @param speed how fast the entity walks
     * @return true if success
     */
    fun moveTo(entity: Entity, speed: Double): Boolean

    /**
     * Will set the entity's walkspeed
     *
     * @param speed walkspeed
     */
    fun setSpeed(speed: Double)

    /**
     * sets active PathEntity to null
     */
    fun clearPathEntity()


}