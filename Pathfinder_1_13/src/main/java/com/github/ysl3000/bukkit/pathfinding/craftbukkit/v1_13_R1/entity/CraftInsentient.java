package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.entity;

import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.pathfinding.CraftNavigation;
import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.pathfinding.CraftPathfinderGoalWrapper;
import com.github.ysl3000.bukkit.pathfinding.entity.Insentient;
import net.minecraft.server.v1_13_R1.ControllerLook;
import net.minecraft.server.v1_13_R1.EntityInsentient;
import net.minecraft.server.v1_13_R1.PathfinderGoal;
import net.minecraft.server.v1_13_R1.PathfinderGoalSelector;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R1.entity.*;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ysl3000
 */
public class CraftInsentient implements Insentient {


    private static Method reset = null;

    static {

        try {
            reset = EntityInsentient.class.getDeclaredMethod("n");
            reset.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    private Map<com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal, PathfinderGoal> nmsGoals = new HashMap<>();
    private Map<com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal, PathfinderGoal> nmsTargetGoals = new HashMap<>();


    private EntityInsentient handle;

    private com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation navigation;

    public CraftInsentient(Flying flying) {
        this(((CraftFlying) flying).getHandle());
    }

    private CraftInsentient(EntityInsentient handle) {
        this.handle = handle;
        this.navigation = new CraftNavigation(handle.getNavigation());

    }


    public CraftInsentient(EnderDragon enderDragon) {
        this(((CraftEnderDragon) enderDragon).getHandle());
    }

    public CraftInsentient(Creature creature) {
        this(((CraftCreature) creature).getHandle());
    }

    public CraftInsentient(Ambient ambient) {
        this(((CraftAmbient) ambient).getHandle());
    }

    public CraftInsentient(Slime slime) {
        this(((CraftSlime) slime).getHandle());
    }

    @Override
    public void addPathfinderGoal(int priority, com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal pathfinderGoal) {
        CraftPathfinderGoalWrapper goalWrapper = new CraftPathfinderGoalWrapper(pathfinderGoal);
        this.nmsGoals.put(pathfinderGoal, goalWrapper);
        handle.goalSelector.a(priority, goalWrapper);
    }

    @Override
    public void removePathfinderGoal(com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal pathfinderGoal) {
        if (nmsGoals.containsKey(pathfinderGoal)) {
            PathfinderGoal nmsGoal = nmsGoals.remove(pathfinderGoal);
            handle.goalSelector.a(nmsGoal);
        }
    }

    @Override
    public boolean hasPathfinderGoal(com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal pathfinderGoal) {
        return nmsGoals.containsKey(pathfinderGoal);
    }

    @Override
    public void clearPathfinderGoals() {
        handle.goalSelector = new PathfinderGoalSelector(handle.getWorld().methodProfiler);
        nmsGoals.clear();
    }


    @Override
    public void addTargetPathfinderGoal(int priority, com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal pathfinderGoal) {
        CraftPathfinderGoalWrapper goalWrapper = new CraftPathfinderGoalWrapper(pathfinderGoal);
        this.nmsTargetGoals.put(pathfinderGoal, goalWrapper);
        handle.targetSelector.a(priority, goalWrapper);
    }

    @Override
    public void removeTargetPathfinderGoal(com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal pathfinderGoal) {
        if (nmsTargetGoals.containsKey(pathfinderGoal)) {
            PathfinderGoal nmsGoal = nmsTargetGoals.remove(pathfinderGoal);
            handle.goalSelector.a(nmsGoal);
        }
    }

    @Override
    public boolean hasTargetPathfinderGoal(com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal pathfinderGoal) {
        return nmsTargetGoals.containsKey(pathfinderGoal);
    }

    @Override
    public void clearTargetPathfinderGoals() {
        handle.targetSelector = new PathfinderGoalSelector(handle.getWorld().methodProfiler);
        nmsTargetGoals.clear();
    }

    @Override
    public void jump() {
        handle.getControllerJump().a();
    }

    /**
     * The entity will look to the given location
     *
     * @param location the entity should look to
     */
    @Override
    public void lookAt(Location location) {
        handle.getControllerLook().a(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    /**
     * The entity will look to the given entity
     *
     * @param entity the entity which is targeted with eyes
     */
    @Override
    public void lookAt(Entity entity) {
        lookAt(entity.getLocation());
    }

    @Override
    public Location getLookingAt() {
        ControllerLook controllerLook = handle.getControllerLook();
        return new Location(handle.getBukkitEntity().getWorld(), controllerLook.e(), controllerLook.f(), controllerLook.g());
    }

    @Override
    public void setMovementDirection(Vector direction, double speed) {
        handle.getControllerMove().a(direction.getX(), direction.getBlockY(), direction.getZ(), speed);
    }

    @Override
    public void setStrafeDirection(float forward, float sideward) {
        handle.getControllerMove().a(forward, sideward);
    }

    @Override
    public com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector getTargetSelector() {
        return new TargetGoalSelector();
    }

    @Override
    public com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector getGoalSelector() {
        return new GoalSelector();
    }

    @Override
    public void resetGoalsToDefault() {
        if (reset == null) return;
        try {
            reset.invoke(handle);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerJump getControllerJump() {
        return new ControllerJump();
    }

    @Override
    public com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerLook getControllerLook() {
        return new ControllerLookImpl();
    }

    @Override
    public com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerMove getControllerMove() {
        return new ControllerMove();
    }

    @Override
    public com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation getNavigation() {
        return navigation;
    }

    @Override
    public float getHeadHeight() {
        return handle.getHeadHeight();
    }

    @Override
    public int getDefaultYaw() {
        return handle.L();
    }

    @Override
    public int getDefaultPitch() {
        return handle.K();
    }

    @Override
    public boolean hasPositionChanged() {
        return handle.positionChanged;
    }

    @Override
    public void onEntityKill(LivingEntity livingEntity) {
        handle.b(livingEntity == null ? null : ((CraftLivingEntity) livingEntity).getHandle());
    }

    @Override
    public Entity getBukkitEntity() {
        return handle.getBukkitEntity();
    }

    @Override
    public void moveRelative(double motionX, double motionY, double motionZ) {
        moveRelative(motionX, motionY, motionZ, 1.0);
    }

    @Override
    public void moveRelative(double motionX, double motionY, double motionZ, double speed) {
        this.handle.motX += (motionX * speed);
        this.handle.motY += (motionY * speed);
        this.handle.motZ += (motionZ * speed);
        this.handle.recalcPosition();
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        this.handle.yaw = yaw;
        this.handle.pitch = pitch;
    }

    @Override
    public void updateRenderAngles() {
        handle.getControllerMove().a();
    }


    private class GoalSelector implements com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector {
        /**
         * Add a pathfinder goal to the entity
         *
         * @param priority the priority 0 highest
         * @param goal     The goal to add
         */
        @Override
        public void addPathfinderGoal(int priority, com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal goal) {
            CraftInsentient.this.addPathfinderGoal(priority, goal);
        }

        /**
         * Remove a pathfinder goal from the entity
         *
         * @param goal The goal to remove
         */
        @Override
        public void removePathfinderGoal(com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal goal) {
            CraftInsentient.this.removePathfinderGoal(goal);
        }

        /**
         * Will clear all goals
         */
        @Override
        public void clearGoals() {
            CraftInsentient.this.clearPathfinderGoals();
        }
    }

    private class TargetGoalSelector implements com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoalSelector {
        /**
         * Add a pathfinder goal to the entity
         *
         * @param priority the priority 0 highest
         * @param goal     The goal to add
         */
        @Override
        public void addPathfinderGoal(int priority, com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal goal) {
            CraftInsentient.this.addTargetPathfinderGoal(priority, goal);
        }

        /**
         * Remove a pathfinder goal from the entity
         *
         * @param goal The goal to remove
         */
        @Override
        public void removePathfinderGoal(com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal goal) {
            CraftInsentient.this.removeTargetPathfinderGoal(goal);
        }

        /**
         * Will clear all goals
         */
        @Override
        public void clearGoals() {
            CraftInsentient.this.clearTargetPathfinderGoals();
        }
    }

    private class ControllerJump implements com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerJump {

        /**
         * Lets the entity jump
         */
        @Override
        public void jump() {
            CraftInsentient.this.jump();
        }
    }

    private class ControllerMove implements com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerMove {

        @Override
        public boolean isOperationMove() {
            return handle.getControllerMove().b();
        }

        @Override
        public void move(double motionX, double motionY, double motionZ, double speed) {
            CraftInsentient.this.setMovementDirection(new Vector(motionX, motionY, motionZ), speed);
        }

        @Override
        public void move(float forward, float sideward) {
            CraftInsentient.this.setStrafeDirection(forward, sideward);
        }

        @Override
        public void update() {
            handle.getControllerMove().a();
        }

        @Override
        public double getX() {
            return handle.getControllerMove().c();
        }

        @Override
        public double getY() {
            return handle.getControllerMove().d();
        }

        @Override
        public double getZ() {
            return handle.getControllerMove().e();
        }

        @Override
        public double getSpeed() {
            return handle.getControllerMove().f();
        }
    }

    private class ControllerLookImpl implements com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerLook {

        /**
         * The entity will look to the given coordinates
         *
         * @param x     x-location
         * @param y     y-location
         * @param z     z-location
         * @param yaw   head rotation yaw
         * @param pitch head rotation pitch
         */
        @Override
        public void lookAt(double x, double y, double z, float yaw, float pitch) {
            CraftInsentient.this.lookAt(new Location(handle.getBukkitEntity().getWorld(), x, y, z, yaw, pitch));
        }

        /**
         * The entity will look to the given location
         *
         * @param location the entity should look to
         */
        @Override
        public void lookAt(Location location) {
            CraftInsentient.this.lookAt(location);
        }

        /**
         * The entity will look to the given entity
         *
         * @param entity the entity which is targeted with eyes
         * @param yaw    head rotation yaw
         * @param pitch  head rotation pitch
         */
        @Override
        public void lookAt(Entity entity, float yaw, float pitch) {
            CraftInsentient.this.lookAt(entity);
        }

        /**
         * Resets the current looking
         */
        @Override
        public void reset() {
            handle.getControllerLook().a();
        }

        /**
         * Checks if the looking is reset
         *
         * @return if looking is default
         */
        @Override
        public boolean isReset() {
            return handle.getControllerLook().b();
        }

        /**
         * Gets the X-Location the entity is looking to
         *
         * @return x-location
         */
        @Override
        public double getLocationX() {
            return handle.getControllerMove().c();
        }

        /**
         * Gets the Y-Location the entity is looking to
         *
         * @return y-location
         */
        @Override
        public double getLocationY() {
            return handle.getControllerMove().d();
        }

        /**
         * Gets the Z-Location the entity is looking to
         *
         * @return z-location
         */
        @Override
        public double getLocationZ() {
            return handle.getControllerMove().f();
        }
    }

}
