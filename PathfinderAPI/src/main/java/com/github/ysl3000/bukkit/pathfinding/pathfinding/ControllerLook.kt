package com.github.ysl3000.bukkit.pathfinding.pathfinding

import org.bukkit.Location
import org.bukkit.entity.Entity


@Deprecated("{{@link com.github.ysl3000.bukkit.pathfinding.entity.Insentient}} Created by Yannick\n" +
        "  on 30.11.2016.")
interface ControllerLook {

    /**
     * Checks if the looking is reset
     *
     * @return if looking is default
     */
    @Deprecated("")
    fun isReset(): Boolean

    /**
     * Use
     *
     * @return x-location
     */
    @Deprecated("{{@link Insentient#getLookingAt()}}\n" +
            "   \n" +
            "    Gets the X-Location the entity is looking to")
    fun getLocationX(): Double

    /**
     * Use
     *
     * @return y-location
     */
    @Deprecated("{{@link Insentient#getLookingAt()}}\n" +
            "   \n" +
            "    Gets the Y-Location the entity is looking to")
    fun getLocationY(): Double

    /**
     * Use
     *
     * @return z-location
     */
    @Deprecated("{{@link Insentient#getLookingAt()}}\n" +
            "   \n" +
            "    Gets the Z-Location the entity is looking to")
    fun getLocationZ(): Double

    /**
     * * Use
     *
     * @param x x-location
     * @param y y-location
     * @param z z-location
     * @param pitch head rotation pitch
     * @param yaw head rotation yaw
     */
    @Deprecated("{{@link com.github.ysl3000.bukkit.pathfinding.entity.Insentient#lookAt(Location)}}\n" +
            "   \n" +
            "    The entity will look to the given coordinates")
    fun lookAt(x: Double, y: Double, z: Double, yaw: Float, pitch: Float)

    /**
     * Use
     *
     * @param location the entity should look to
     */
    @Deprecated("{{@link com.github.ysl3000.bukkit.pathfinding.entity.Insentient#lookAt(Location)}}\n" +
            "   \n" +
            "    The entity will look to the given location")
    fun lookAt(location: Location)

    /**
     * Use
     *
     * @param entity the entity which is targeted with eyes
     * @param pitch head rotation pitch
     * @param yaw head rotation yaw
     */
    @Deprecated("{{@link com.github.ysl3000.bukkit.pathfinding.entity.Insentient#lookAt(Entity)}}\n" +
            "    The entity will look to the given entity")
    fun lookAt(entity: Entity, yaw: Float, pitch: Float)

    /**
     * Resets the current looking
     */
    @Deprecated("")
    fun reset()


}