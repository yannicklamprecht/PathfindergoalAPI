package org.bukkit.pathfinding;

/**
 * Created by Yannick on 30.11.2016.
 */
public interface ControllerJump {
    /**
     * Lets the entity jump
     */
    void jump();

    /**
     * Sets whether the entity should jump all time (don't know what it does)
     */
    void setJumping();
}