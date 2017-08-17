package org.bukkit.pathfinding;

import org.bukkit.entity.Player;

/**
 *
 * The PathfinderPlayer grants access to functionality needed for PathfinderEntities
 * e.g. get the relative Motion of the player
 *
 */
public interface PathfinderPlayer {

    /**
     *
     * Returns the Bukkit Player
     *
     * @return Bukkit Player
     */
    Player getPlayer();

    double getRelativeMotionX();
    double getRelativeMotionY();
    double getRelativeMotionZ();

    float getRelativeMotionYaw();

    float getRelativeMotionPitch();

    float getMotionForward();

    float getMotionSideward();

    boolean isJumping();

}