package org.bukkit.pathfinding;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

/**
 * Created by Yannick on 30.11.2016.
 */
public interface Insentient {

    /**
     * Will return the PathfinderGoalTargetSelector
     *
     * @return targetSelector
     */
    PathfinderGoalSelector getTargetSelector();
    /**
     * Will return the PathfinderGoalSelector
     *
     * @return selector
     */
    PathfinderGoalSelector getGoalSelector();

    /**
     * Will reset goals to default one
     *
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
     * @param livingEntity
     */
    void onEntityKill(LivingEntity livingEntity);

    /**
     * Will return the LivingEntity of Bukkit
     *
     * @return entity
     */
    Entity getBukkitEntity();



    /**
     * Will move the Entity relative
     * @param motionX relative motionX
     * @param motionY relative motionY
     * @param motionZ relative motionZ
     */
    void moveRelative(double motionX, double motionY ,double motionZ);


    /**
     * Will move the Entity relative
     * @param motionX relative motionX
     * @param motionY relative motionY
     * @param motionZ relative motionZ
     * @param speed the speed multiplier
     */
    void moveRelative(double motionX, double motionY ,double motionZ, double speed);


    /**
     *
     * Rotates the entity
     *
     * @param yaw  absolute yaw
     * @param pitch absolute pitch
     */
    void setRotation(float yaw, float pitch);


    /**
     * Updates the the angles for rendering
     */
    void updateRenderAngles();

}
