package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.entity;

import com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.pathfinding.*;
import com.github.ysl3000.bukkit.pathfinding.entity.Insentient;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.*;
import net.minecraft.server.v1_13_R1.EntityInsentient;
import org.bukkit.craftbukkit.v1_13_R1.entity.*;
import org.bukkit.entity.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    private EntityInsentient handle;

    private ControllerJump controllerJump;
    private ControllerLook controllerLook;
    private ControllerMove controllerMove;
    private Navigation navigation;

    private PathfinderGoalSelector goalSelector;
    private PathfinderGoalSelector targetSelector;


    public CraftInsentient(Flying flying) {
        this(((CraftFlying) flying).getHandle());
    }

    private CraftInsentient(EntityInsentient handle) {
        this.handle = handle;
        this.controllerJump = new CraftControllerJump(handle.getControllerJump());
        this.controllerLook = new CraftControllerLook(handle.getControllerLook());
        this.controllerMove = new CraftControllerMove(handle.getControllerMove());
        this.navigation = new CraftNavigation(handle.getNavigation());
        this.goalSelector = new CraftPathfinderGoalSelector(handle.getWorld().methodProfiler, handle.goalSelector);
        this.targetSelector = new CraftPathfinderGoalSelector(handle.getWorld().methodProfiler, handle.targetSelector);

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
    public PathfinderGoalSelector getTargetSelector() {
        return targetSelector;
    }

    @Override
    public PathfinderGoalSelector getGoalSelector() {
        return goalSelector;
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
    public ControllerJump getControllerJump() {
        return controllerJump;
    }

    @Override
    public ControllerLook getControllerLook() {
        return controllerLook;
    }

    @Override
    public ControllerMove getControllerMove() {
        return controllerMove;
    }

    @Override
    public Navigation getNavigation() {
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
        controllerMove.update();
    }


}
