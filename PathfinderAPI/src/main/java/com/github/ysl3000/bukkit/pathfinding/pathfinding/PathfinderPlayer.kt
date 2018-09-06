package com.github.ysl3000.bukkit.pathfinding.pathfinding

import org.bukkit.entity.Player

/**
 * The PathfinderPlayer grants access to functionality needed for PathfinderEntities e.g. get the
 * relative Motion of the player
 */
interface PathfinderPlayer {

    /**
     * Returns the Bukkit Player
     *
     * @return Bukkit Player
     */
    fun getPlayer(): Player

    fun getRelativeMotionX(): Double

    fun getRelativeMotionY(): Double

    fun getRelativeMotionZ(): Double

    fun getRelativeMotionYaw(): Float

    fun getRelativeMotionPitch(): Float

    fun getMotionForward(): Float

    fun getMotionSideward(): Float

    fun isJumping(): Boolean

}